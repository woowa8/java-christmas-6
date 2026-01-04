package christmas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {
    public List<List<String>> parse(String input) {
        List<List<String>> mainList = new ArrayList<>();

        String[] lists = input.split(",");

        for(String list : lists){
            validateInput(list);
            String[] parse = input.split("-");
            validateNumber(Integer.parseInt(parse[1]));

            List<String> subList = new ArrayList<>();
            subList.add(parse[0]);
            subList.add(parse[1]);

            mainList.add(subList);
        }
        return mainList;
    }

    // 1. 해산물파스타-2 정규 표현식인지 검증
    private void validateInput(String list) {
        if(!list.matches("[가-힣]+-[0-9]+")){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNumber(int number) {
        if(number <= 0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
