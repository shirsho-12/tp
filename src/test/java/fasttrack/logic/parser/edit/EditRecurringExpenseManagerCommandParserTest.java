package fasttrack.logic.parser.edit;

import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.edit.EditRecurringExpenseManagerCommand;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;

public class EditRecurringExpenseManagerCommandParserTest {

    public static final String MESSAGE_INVALID_INDEX = "The index provided is invalid.";
    private final EditRecurringExpenseManagerCommandParser parser = new EditRecurringExpenseManagerCommandParser();
    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_noArgumentsExceptIndex_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditRecurringExpenseManagerCommand expectedCommand = new EditRecurringExpenseManagerCommand(toUseForExpected,
                null, null, null, null, null);
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_onlyValidPriceChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditRecurringExpenseManagerCommand expectedCommand = new EditRecurringExpenseManagerCommand(toUseForExpected,
                null, 4.50, null, null, null);
        assertParseSuccess(parser, "1 p/4.50", expectedCommand);
    }

    @Test
    public void parse_onlyValidCategoryChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditRecurringExpenseManagerCommand expectedCommand = new EditRecurringExpenseManagerCommand(toUseForExpected,
                null, null, "NewCategory", null, null);
        assertParseSuccess(parser, "1 c/NewCategory", expectedCommand);
    }

    @Test
    public void parse_onlyValidEndDateChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditRecurringExpenseManagerCommand expectedCommand = new EditRecurringExpenseManagerCommand(toUseForExpected,
                null, null, null, null,
                ParserUtil.parseDate("24/3/23"));
        assertParseSuccess(parser, "1 ed/24/3/23", expectedCommand);
    }

    @Test
    public void parse_invalidEndDateChange_success() throws ParseException {
        assertParseFailure(parser, "1 ed/2023/03/21", "Invalid date format! Please use DD/MM/YY format!");
    }
}
