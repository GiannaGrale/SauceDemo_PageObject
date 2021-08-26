package endpoints;

public interface CasesEndpoints {
    String GET_CASE ="index.php?/api/v2/get_case/%d";
    String GET_CASES ="index.php?/api/v2/get_cases/%d&suite_id=%d";
    String GET_HISTORY_CASES  = "index.php?/api/v2/get_history_for_case/%d";
    String ADD_CASE = "index.php?/api/v2/add_case/%d";
    String COPY_CASES_TO_SECTION = "index.php?/api/v2/copy_cases_to_section/%d";
    String UPDATE_CASE = "index.php?/api/v2/update_case/%d";
    String UPDATE_CASES =  "index.php?/api/v2/update_cases/%d";
    String MOVE_CASES_TO_SECTION = "index.php?/api/v2/move_cases_to_section/%d";
    String DELETE_TESTCASE = "index.php?/api/v2/delete_case/%d";
    String DELETE_CASES = "index.php?/api/v2/delete_cases/%d&%d";
}
