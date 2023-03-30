package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.model.util.UserInterfaceUtil;

/**
 * A UI component that displays information of a {@code RecurringExpenseManager}.
 */
public class RecurringExpenseCard extends UiPart<Region> {

    private static final String FXML = "RecurringExpenseListCard.fxml";

    public final RecurringExpenseManager recurringExpenseManager;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label expenseName;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private Label frequency;

    /**
     * Creates a {@code RecurringExpenseCard} with the given {@code RecurringExpenseManager} and index to display.
     */
    public RecurringExpenseCard(RecurringExpenseManager recurringExpenseManager, int displayedIndex) {
        super(FXML);
        this.recurringExpenseManager = recurringExpenseManager;
        id.setText(displayedIndex + ". ");
        expenseName.setText(UserInterfaceUtil.capitalizeFirstLetter(recurringExpenseManager.getExpenseName()));
        String categoryName = recurringExpenseManager.getExpenseCategory().getCategoryName();
        category.setText(UserInterfaceUtil.capitalizeFirstLetter(categoryName));
        price.setText(UserInterfaceUtil.parsePrice(recurringExpenseManager.getExpenseAmount()));
        String recurringExpenseFrequency = recurringExpenseManager.getRecurringExpenseType().name();
        frequency.setText(UserInterfaceUtil.capitalizeFirstLetter(recurringExpenseFrequency.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RecurringExpenseCard)) {
            return false;
        }
        // state check
        RecurringExpenseCard card = (RecurringExpenseCard) other;
        return id.getText().equals(card.id.getText())
                && recurringExpenseManager.equals(card.recurringExpenseManager);
    }
}
