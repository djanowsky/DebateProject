package debate.gui;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * TableRenderDemo.java requires no other files.
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import debate.core.Debate;
import debate.core.Team;
import debate.core.Tournament;

/**
 * @author winstono
 *
 */
public class TableRenderer extends JPanel implements TableModelListener
{
	private boolean DEBUG = false;

	private Tournament tournament;

	private static final int AFF_COL = 0;
	private static final int NEG_COL = 1;
	private static final int JUDGE_COL = 2;
	private static final int TIMER_COL = 3;

	private DefaultCellEditor cellEditor;
	private JComboBox comboBox;
	

	private Timer timer;

	private MyTableModel tableModel;
	private JTable table;

	public TableRenderer(Tournament tournament)
	{
		super(new GridLayout(1, 0));
		this.tournament = tournament;
		
		

		timer = new Timer(5, null);
		tableModel = new MyTableModel();
		table = new JTable(tableModel);
		table.setDragEnabled(false);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		tableModel.addTableModelListener(this);

		comboBox = new JComboBox();
		cellEditor = new DefaultCellEditor(comboBox);

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		table.setRowHeight(30);
		TableColumn column = null;
		for (int i = 0; i < 4; i++)
		{
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(50);
		}
		// Set up column sizes.
		initColumnSizes(table);

		// Fiddle with the Aff/Neg column's cell editors/renderers.
		setUpAffirmativeColumn(table.getColumnModel().getColumn(0));

		setUpNegativeColumn(table.getColumnModel().getColumn(1));

		// Add the scroll pane to this panel.
		add(scrollPane);
		updateComboBox();
		timer.start();
	}

	/*
	 * This method picks good column sizes. If all column heads are wider than
	 * the column's cells' contents, then you can just use
	 * column.sizeWidthToFit().
	 */
	private void initColumnSizes(JTable table)
	{
		MyTableModel model = (MyTableModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < model.tableData.get(0).size(); i++)
		{
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table,
					longValues[i], false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;

			if (DEBUG)
			{
				System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
						+ "; cellWidth = " + cellWidth);
			}

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}

	}

	public void setUpAffirmativeColumn(TableColumn affColumn)
	{

		affColumn.setCellEditor(cellEditor);

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to change team");
		affColumn.setCellRenderer(renderer);
	}

	private void updateComboBox()
	{
		comboBox.removeAllItems();
		if (tournament.getExtraTeam() == null)
			comboBox.setEnabled(false);
		else
		{
			comboBox.setEnabled(true);
			comboBox.addItem(tournament.getExtraTeam());
			
		}
		
		
	}

	public void setUpNegativeColumn(TableColumn negColumn)
	{
		// negTeamComboBox = new ArrayList<Object>();
		// Set up the editor for the sport cells.

		negColumn.setCellEditor(cellEditor);

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to change team");
		negColumn.setCellRenderer(renderer);
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] columnIdentifiers = { "Room", "Affirmative", "Negative", "Judge", "Timer" };

		private ArrayList<ArrayList<Object>> tableData = new ArrayList<ArrayList<Object>>();
		private ArrayList<ArrayList<Object>> tableDataCopy = new ArrayList<ArrayList<Object>>();

		MyTableModel()
		{
			generateTable();
			initTableCopy();
		}

		private void generateTable()
		{
			List<Debate> debates = tournament.getCurrentRound().getDebates();

			for (Debate d : debates)
			{
				ArrayList<Object> debateInfo = new ArrayList<Object>();


				try
				{
					debateInfo.add(d.getAffTeam());
				} catch (NullPointerException e)
				{
					debateInfo.add("N/A");
				}

				try
				{
					debateInfo.add(d.getNegTeam());
				} catch (NullPointerException e)
				{
					debateInfo.add("N/A");
				}

				try
				{
					debateInfo.add(d.getJudge());
				} catch (NullPointerException e)
				{
					debateInfo.add("N/A");
				}

				debateInfo.add(new Boolean(false));

				tableData.add(debateInfo);
			}
		}

		private void initTableCopy()
		{
			tableDataCopy = new ArrayList<ArrayList<Object>>();

			for (int row = 0; row < tableData.size(); row++)
			{
				tableDataCopy.add(new ArrayList<Object>());
				for (int col = 0; col < tableData.get(row).size(); col++)
				{
					if (col == AFF_COL || col == NEG_COL)
					{
						tableDataCopy.get(row).add(new Team((Team) tableData.get(row).get(col)));
					} else
						tableDataCopy.get(row).add(tableData.get(row).get(col));
				}
			}
		}

		public final Object[] longValues = { "A245", "AA1", "BB1", new Integer(20), Boolean.TRUE };

		public int getColumnCount()
		{
			return tableData.get(0).size();
		}

		public int getRowCount()
		{
			return tableData.size();
		}

		public String getColumnName(int col)
		{
			return columnIdentifiers[col];
		}

		public Object getValueAt(int row, int col)
		{
			return tableData.get(row).get(col);
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		public Class getColumnClass(int c)
		{
			if (getValueAt(0, c) == null)
			{
				System.out.println(c);
			}

			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col)
		{
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 0 || tournament.getExtraTeam() == null)
			{
				return false;
			} else
			{
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		public void setValueAt(Object value, int row, int col)
		{
			if (DEBUG)
			{
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			tableData.get(row).set(col, value);
			// updateTable(value, row, col);
			fireTableCellUpdated(row, col);

			if (DEBUG)
			{
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData()
		{
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++)
			{
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++)
				{
					System.out.print("  " + tableData.get(i).get(j));
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}

	}

	@Override
	public void tableChanged(TableModelEvent e)
	{

		int row = e.getLastRow();
		int col = e.getColumn();

		System.out.println(tournament.getCurrentRound());

		Object old = getChangedValue(row, col);
		Object newVal = table.getValueAt(row, col);

		if (col == AFF_COL || col == NEG_COL)
			switchExtraTeam((Team) newVal, (Team) old);

		updateTableData(row, col);

		updateComboBox();

		System.out.println(tournament.getCurrentRound());

	}

	private Object getChangedValue(int row, int col)
	{
		return tableModel.tableDataCopy.get(row).get(col);
	}

	private void switchExtraTeam(Team oldExtra, Team newExtra)
	{
		tournament.setExtraTeam(newExtra);

		for (Debate d : tournament.getCurrentRound().getDebates())
		{
			if (d.getAffTeam().equals(newExtra))
			{
				d.setAffTeam(oldExtra);
				break;
			}

			if (d.getNegTeam().equals(newExtra))
			{
				d.setNegTeam(oldExtra);
				break;
			}
		}

	}

	private void updateTableData(int changedRow, int changedCol)
	{
		tableModel.tableDataCopy.get(changedRow).set(changedCol, table.getValueAt(changedRow, changedCol));
	}

}
