package project.examination.com.examination;

import java.util.List;

/**
 * Created by nutnicha on 1/12/2017.
 */

public class GetDepartment {

    private List<OutputBean> output;

    public List<OutputBean> getOutput() {
        return output;
    }

    public void setOutput(List<OutputBean> output) {
        this.output = output;
    }

    public static class OutputBean {
        /**
         * department_id : 1
         * department_name : วิชาความถนัดศิลปกรรม
         * department_branch : [{"branch_id":1,"branch_name":"ดนตรีไทย"},{"branch_id":4,"branch_name":"นาฏศิลป์"},{"branch_id":5,"branch_name":"ศิลปะ"},{"branch_id":6,"branch_name":"องค์ประกอบศิลปะ"},{"branch_id":7,"branch_name":"ศิลปะการแสดง"},{"branch_id":8,"branch_name":"ดนตรีสากล"}]
         */

        private int department_id;
        private String department_name;
        private List<DepartmentBranchBean> department_branch;

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public List<DepartmentBranchBean> getDepartment_branch() {
            return department_branch;
        }

        public void setDepartment_branch(List<DepartmentBranchBean> department_branch) {
            this.department_branch = department_branch;
        }

        public static class DepartmentBranchBean {
            /**
             * branch_id : 1
             * branch_name : ดนตรีไทย
             */

            private int branch_id;
            private String branch_name;

            public int getBranch_id() {
                return branch_id;
            }

            public void setBranch_id(int branch_id) {
                this.branch_id = branch_id;
            }

            public String getBranch_name() {
                return branch_name;
            }

            public void setBranch_name(String branch_name) {
                this.branch_name = branch_name;
            }
        }
    }
}
