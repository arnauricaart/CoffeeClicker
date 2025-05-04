package Presentation.views;

import Business.Entities.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

public class ShowGamesView extends JFrame {

    private JTable gameTable;
    private JTextField userSearchField;
    private JTextField gameSearchField;
    private JButton searchButton;
    private ActionListener showStatsActionListener;
    private ActionListener searchActionListener;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JPanel statsPanel;
    private JSplitPane splitPane;

    public ShowGamesView(List<Game> games, boolean isFinishedGames) {
        setTitle("Game Statistics");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create left panel for game list
        JPanel leftPanel = new JPanel(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel userSearchLabel = new JLabel("User name:");
        userSearchField = new JTextField(15);
        JLabel gameSearchLabel = new JLabel("Game name:");
        gameSearchField = new JTextField(15);
        searchButton = new JButton("Search");
        
        searchPanel.add(userSearchLabel);
        searchPanel.add(userSearchField);
        searchPanel.add(gameSearchLabel);
        searchPanel.add(gameSearchField);
        searchPanel.add(searchButton);
        
        leftPanel.add(searchPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"IdPartida", "User name", "Game name", "Coffees", "Last Access"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0 || column == 3) { // IdPartida and Coffees columns
                    return Integer.class;
                }
                return String.class;
            }
        };

        updateTableData(games);

        gameTable = new JTable(tableModel);
        gameTable.setFont(new Font("Arial", Font.PLAIN, 16));
        gameTable.setRowHeight(30);
        gameTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        gameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sorter = new TableRowSorter<>(tableModel);
        gameTable.setRowSorter(sorter);

        sorter.setComparator(4, (Comparator<String>) (date1, date2) -> {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return format.parse(date1).compareTo(format.parse(date2));
            } catch (ParseException e) {
                return date1.compareTo(date2);
            }
        });

        gameTable.getColumnModel().getColumn(1).setCellRenderer(new AlternatingColorRenderer(JLabel.LEFT));
        gameTable.getColumnModel().getColumn(2).setCellRenderer(new AlternatingColorRenderer(JLabel.LEFT));
        gameTable.getColumnModel().getColumn(3).setCellRenderer(new AlternatingColorRenderer(JLabel.CENTER));
        gameTable.getColumnModel().getColumn(4).setCellRenderer(new AlternatingColorRenderer(JLabel.RIGHT));

        TableColumnModel columnModel = gameTable.getColumnModel();
        TableColumn hiddenColumn = columnModel.getColumn(0);
        columnModel.removeColumn(hiddenColumn);

        gameTable.getTableHeader().setReorderingAllowed(false);
        gameTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gameTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameTable.getSelectedRow() != -1) {
                    showStatsActionListener.actionPerformed(null);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gameTable);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // Create right panel for stats
        statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBackground(Color.WHITE);
        JLabel placeholderLabel = new JLabel("Select a game to view statistics", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsPanel.add(placeholderLabel, BorderLayout.CENTER);

        // Create split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, statsPanel);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(0.5);
        
        add(splitPane, BorderLayout.CENTER);
    }

    public void updateTableData(List<Game> games) {
        tableModel.setRowCount(0);
        for (Game game : games) {
            Object[] row = {game.getGameID(), game.getUserName(), game.getName(), game.getCoffees(), game.getLastAccess()};
            tableModel.addRow(row);
        }
    }

    public void updateStatsChart(List<Integer> cafesPorMinuto) {
        statsPanel.removeAll();
        CafeStatsPanel statsChart = new CafeStatsPanel(cafesPorMinuto);
        statsPanel.add(statsChart, BorderLayout.CENTER);
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    public String getUserSearchText() {
        return userSearchField.getText().trim();
    }

    public String getGameSearchText() {
        return gameSearchField.getText().trim();
    }

    public void setSearchActionListener(ActionListener l) {
        searchButton.addActionListener(l);
        
        // Also add action listeners to text fields for "Enter" key
        ActionListener al = e -> l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        userSearchField.addActionListener(al);
        gameSearchField.addActionListener(al);
    }

    public int getCurrentPartidaId(){
        int selectedRow = gameTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = gameTable.convertRowIndexToModel(selectedRow);
            Integer hiddenValue = (Integer) gameTable.getModel().getValueAt(modelRow, 0);
            return hiddenValue;
        }
        return -1;
    }

    public void setShowStatsActionListener(ActionListener l) {
        this.showStatsActionListener = l;
    }

    public String getSelectedGameName()  {
        int selectedRow = gameTable.getSelectedRow();
        String gameName = null;
        if (selectedRow != -1) {
            gameName = (String) gameTable.getValueAt(selectedRow, 0);
        }
        return gameName;
    }

    private static class AlternatingColorRenderer extends DefaultTableCellRenderer {
        private final int alignment;

        public AlternatingColorRenderer(int alignment) {
            this.alignment = alignment;
            setHorizontalAlignment(alignment);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? new Color(230, 230, 230) : Color.WHITE);
            } else {
                c.setBackground(new Color(184, 207, 229));
            }
            return c;
        }
    }
}
