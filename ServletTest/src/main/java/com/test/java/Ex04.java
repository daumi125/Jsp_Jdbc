package com.test.java;

public class Ex04 {
    public static void main(String[] args) {
        /*
         * 
         * 자바의 문자열
         * 1."string"
         * 2."""string"""
         * 
         * """string"""
         * - Text Blocks
         * - JDK 17이상
         * 
         * 일반 문자열과 차이점
         * 1. """ 사용
         * 2. 줄바꿈 가능
         * 3. 들여쓰기 처리
         * 4. 이스케이프 자동 처리
         * 5. 개행 문자 통일
         * 
         * 
         * */
        
        //- 시작하는 """는 다른 내용이 뒤따라오면 안된다.
        //- 끝나는 """는 앞에 다른 내용이 있어도 된다.
        String txt1 = """
                문자열
                하나
                둘
                셋
                """;
        
        System.out.println(txt1);
        
        //- 시작 """부터 처음 만나는 내용까지의 공백은 무시
        //- 모든 라인에서 가장 왼쪽에 있는 문장이 기준(그 앞 공백은 자동으로 사라짐)
         
        String txt2 = """
                문자열
                    하나
                    둘
                셋
                """; //왼쪽 라인은 이것도 포함되니까 주의할 것
        
        System.out.println(txt2);
        
        //내부적으로 모든 엔터들이 LF(\n)로 통일된다.
        String txt3 = """
                문자열\r\n
                    하나\r
                    둘\n
                셋
                """; //왼쪽 라인은 이것도 포함되니까 주의할 것
        
        System.out.println(txt3);
        
        //이스케이프 안해도 됨
        String txt4 = """
                문자열
                하나
                안녕하세요. "홍길동"입니다.
                둘
                셋
                """;
        
        System.out.println(txt4);
        
        //한 줄 출력시 (역슬래시) > 문자열 하나 둘 셋 
        String txt5 = """
                문자열 \
                하나 \
                둘 \
                셋 \
                """;
        
        System.out.println(txt5);
    }
}
