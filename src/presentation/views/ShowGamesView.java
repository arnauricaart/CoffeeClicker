package presentation.views;

import business.entities.Game;

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

/**
 * This class extends from JFrame, the class implements a view that will let the user see his played games stats.
 */
public class ShowGamesView extends JFrame {

    /**
     * JTable where the list of games will be.
     */
    private JTable gameTable;
    /**
     * JTextField where the user will put the user name to filter the games.
     */
    private JTextField userSearchField;
    /**
     * JTextField where the user will put the game name to filter the games.
     */
    private JTextField gameSearchField;
    /**
     * JButton that will start the search.
     */
    private JButton searchButton;
    /**
     * Action Listener that will show the stats.
     */
    private ActionListener showStatsActionListener;
    /**
     * Action Listener that will search the games.
     */
    private ActionListener searchActionListener;
    /**
     * Default Table Model
     */
    private DefaultTableModel tableModel;
    /**
     * Table Row Sorter to sort the tableModel atribute.
     */
    private TableRowSorter<DefaultTableModel> sorter;
    /**
     * JPanel where the stats will be shown.
     */
    private JPanel statsPanel;
    /**
     * JSplitPane that lets us divide the view in 2 parts, the search of the game and the stats.
     */
    private JSplitPane splitPane;
    /**
     * JButton that lets us go back to the previous screen.
     */
    private JButton returnButton;
    /**
     * Action Listener that will return the to the previous screen.
     */
    private ActionListener returnActionListener;

    /**
     * Constructor of the class.
     * @param games List of games.
     * @param isFinishedGames Boolean that tells if the game is finished or not.
     */
    public ShowGamesView(List<Game> games, boolean isFinishedGames) {
        setTitle("Game Statistics");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create left
        JPanel leftPanel = new JPanel(new BorderLayout());

        returnButton = new JButton("Return to menu");


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

        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BorderLayout());
        topLeftPanel.add(returnButton, BorderLayout.NORTH);
        topLeftPanel.add(searchPanel, BorderLayout.CENTER);

        leftPanel.add(topLeftPanel, BorderLayout.NORTH);


        // Table setup
        String[] columnNames = {"IdPartida", "User name", "Game name", "Coffees", "Last Access"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0 || column == 3) {
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

        // Create right panel
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

    /**
     * This method updates the datable data to show the new games (after filtering).
     * @param games List of the new games that will show in the table.
     */
    public void updateTableData(List<Game> games) {
        tableModel.setRowCount(0);
        for (Game game : games) {
            Object[] row = {game.getGameID(), game.getUserName(), game.getName(), (int)game.getNumCoffees(), game.getLastAccess()};
            tableModel.addRow(row);
        }
    }

    /**
     * This method updates the stats chart.
     * @param cafesPorMinuto List with number (coffes) in every minute.
     */
    public void updateStatsChart(List<Integer> cafesPorMinuto) {
        statsPanel.removeAll();
        CafeStatsPanel statsChart = new CafeStatsPanel(cafesPorMinuto);
        statsPanel.add(statsChart, BorderLayout.CENTER);
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    /**
     * This method gets the user search text, if it is superior to 150, we inform the user that it is capped.
     * @return Returns a string with the user name.
     */
    public String getUserSearchText() {
        String text = userSearchField.getText().trim();
        if(text.length() > 150){
            new PopUpView("The username can only accept a maximum of 150characters.");
            return text.substring(0, 150);
        }else{
            return text;
        }
    }

    /**
     * This method gets the game name for the search, if it is superior to 150, we inform the user that it is capped.
     * @return Returns a string with the game name.
     */
    public String getGameSearchText() {
        String text = gameSearchField.getText().trim();
        if(text.length() > 150){
            new PopUpView("The game name can only accept a maximum of 150characters.");
            return text.substring(0, 150);
        }else{
            return text;
        }
    }

    /**
     * This method sets an action listener to the search button.
     * @param l Action Listener that will tell the search button what to do.
     */
    public void setSearchActionListener(ActionListener l) {
        searchButton.addActionListener(l);
        ActionListener al = e -> l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        userSearchField.addActionListener(al);
        gameSearchField.addActionListener(al);
    }

    /**
     * This method returns the selected game's id.
     * @return Returns a number with the game id.
     */
    public int getCurrentPartidaId(){
        int selectedRow = gameTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = gameTable.convertRowIndexToModel(selectedRow);
            Integer hiddenValue = (Integer) gameTable.getModel().getValueAt(modelRow, 0);
            return hiddenValue;
        }
        return -1;
    }

    /**
     * This method sets an action listener to the show stats atribute.
     * @param l Action listener that will be saved on showStats.
     */
    public void setShowStatsActionListener(ActionListener l) {
        this.showStatsActionListener = l;
    }

    /**
     * This method sets an action listener to the return button.
     * @param l Action listener that will be saved on this class.
     */
    public void setReturnActionListener(ActionListener l) {
        this.returnActionListener = l;
        returnButton.addActionListener(l);
    }

    /**
     * This method returns the selected game's name.
     * @return Returns a string with the game name.
     */
    public String getSelectedGameName()  {
        int selectedRow = gameTable.getSelectedRow();
        String gameName = null;
        if (selectedRow != -1) {
            gameName = (String) gameTable.getValueAt(selectedRow, 0);
        }
        return gameName;
    }

    /**
     * Class that extends from DefaultTableCellRenderer.
     */
    private static class AlternatingColorRenderer extends DefaultTableCellRenderer {
        /**
         * Number with the alignment.
         */
        private final int alignment;

        /**
         * Constructor of the class.
         * @param alignment
         */
        public AlternatingColorRenderer(int alignment) {
            this.alignment = alignment;
            setHorizontalAlignment(alignment);
        }

        /**
         * This method gets the table cell renderer component.
         * @param table  the <code>JTable</code>
         * @param value  the value to assign to the cell at <code>[row, column]</code>
         * @param isSelected true if cell is selected
         * @param hasFocus true if cell has focus
         * @param row  the row of the cell to render
         * @param column the column of the cell to render
         * @return Returns a Component.
         */
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
