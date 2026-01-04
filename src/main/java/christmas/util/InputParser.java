package christmas.util;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public List<List<String>> parsing(String input) {
        String[] parsed = input.split(",");
        List<List<String>> mainList = new ArrayList<>();
        // ,로 자른 뒤 형식이 - 가 아닐 경우
        for(String s : parsed) {
            List<String> subList = new ArrayList<>();
            if(!s.matches("[가-힣]+-[0-9]+")){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            String[] split = s.split("-");

            if(Integer.parseInt(split[1]) <= 0){    // 메뉴 갯수 1 이상
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            subList.add(split[0]);
            subList.add(split[1]);

            mainList.add(subList);
        }
        return mainList;
    }
}
