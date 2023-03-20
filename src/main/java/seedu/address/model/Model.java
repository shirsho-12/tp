package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * The API of the DataModel component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;
    Predicate<Category> PREDICATE_SHOW_ALL_CATEGORY = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getExpenseTrackerFilePath();

    /**
     * Sets the user prefs' expense tracker file path.
     */
    void setExpenseTrackerFilePath(Path expenseTrackerFilePath);

    /**
     * Replaces expenseTracker data with the data in {@code expenseTracker}.
     */
    void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker);

    /** Returns the ExpenseTracker */
    ReadOnlyExpenseTracker getExpenseTracker();

    // Expense accessor functions

    /**
     * Adds the given expense to the expense tracker
     * @param expense the new expense to add
     */
    void addExpense(Expense expense);

    /**
     * Deletes the given expense.
     * The expense must exist in the ExpenseTracker.
     * @param expense the expense to delete
     */
    void deleteExpense(Expense expense);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense list
     */
    void setExpense(Expense target, Expense editedExpense);


    /**
     * Indicates if an expense exists in the expense list
     * @param expense the expense to check for
     */
    boolean hasExpense(Expense expense);

    /**
     * Updates the filter of the filtered expense list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpensesList(Predicate<Expense> predicate);

    /** Returns an unmodifiable view of the filtered expense list */
    ObservableList<Expense> getFilteredExpenseList();

    // Category accessor functions

    /**
     * Deletes the given expense.
     * @param target the category to delete
     */
    void deleteCategory(Category target);

    /**
     * Adds the given category to the category list.
     * @param toAdd the category to add
     */
    void addCategory(Category toAdd);


    /**
     * Indicates if a category exists in the category list
     * @param category the category to check for
     */
    boolean hasCategory(Category category);

    /** Returns an unmodifiable view of the category list */
    ObservableList<Category> getFilteredCategoryList();

    /**
     * Updates the filter of the filtered category list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCategoryList(Predicate<Category> predicate);

    /**
     * Returns a reference to the instance of category
     * matching the category name in the category list
     * @param categoryName the category name to check for
     * @return the category instance if it exists, and null if it does not
     */
    Category getCategoryInstance(String categoryName);
}
