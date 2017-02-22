package project.examination.com.examination;

import java.util.List;

/**
 * Created by nutnicha on 1/12/2017.
 */

public class GetExamination {

    private List<OutputBean> output;

    public List<OutputBean> getOutput() {
        return output;
    }

    public void setOutput(List<OutputBean> output) {
        this.output = output;
    }

    public static class OutputBean {
        /**
         * id : 10
         * question : เสียงควบกล้ำในข้อใดไม่ปรากฏในระบบเสียงภาษาไทย
         * image : http://examination.strokenu.com/uploaded/examination/10/18e4376e9c94ab1150ed352da9f658f2.jpg
         * department : ถนัดศิลปกรรม
         * department_branch : ดนตรีไทย
         * level_txt : ง่าย
         * answer : [{"answer":"บรั่นดี","is_correct":0},{"answer":"นิวเคลียส","is_correct":0},{"answer":"อิเควเตอร์ ","is_correct":0},{"answer":"เพนกวิน","is_correct":1}]
         */

        private int id;
        private String question;
        private String image;
        private String department;
        private String department_branch;
        private String level_txt;
        private List<AnswerBean> answer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDepartment_branch() {
            return department_branch;
        }

        public void setDepartment_branch(String department_branch) {
            this.department_branch = department_branch;
        }

        public String getLevel_txt() {
            return level_txt;
        }

        public void setLevel_txt(String level_txt) {
            this.level_txt = level_txt;
        }

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public static class AnswerBean {
            /**
             * answer : บรั่นดี
             * is_correct : 0
             */

            private String answer;
            private int is_correct;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public int getIs_correct() {
                return is_correct;
            }

            public void setIs_correct(int is_correct) {
                this.is_correct = is_correct;
            }
        }
    }
}
