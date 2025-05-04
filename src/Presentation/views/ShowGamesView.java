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

    public ShowGamesView(List<Game> games, boolean isFinishedGames) {
        setTitle("Continue Game");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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
        
        add(searchPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"IdPartida", "User name", "Player name", "Coffees", "Last Access"};
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
                if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                    showStatsActionListener.actionPerformed(null);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gameTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateTableData(List<Game> games) {
        tableModel.setRowCount(0);
        for (Game game : games) {
            Object[] row = {game.getGameID(), game.getUserName(), game.getName(), game.getCoffees(), game.getLastAccess()};
            tableModel.addRow(row);
        }
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
            int modelRow= gameTable.convertRowIndexToModel(selectedRow);
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
