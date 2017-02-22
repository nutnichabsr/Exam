package project.examination.com.examination;

import org.json.JSONArray;

import java.util.List;

public final class File_Confix_Data {

 	public static List<GetExamination.OutputBean> getOutput_data_List;
	public static List<GetDepartment.OutputBean> getGetDepartment;
	public static List<GetStudentData.OutputBean> getGetStudentData;

	public static String select_type_sex[] ;

	public static int potition=0;  ;
	public static int DepartmentId=0;  ;
	public static int DepartmentBranchId=0;  ;
	public static String student_code="";  ;

	public static String Code="";  ;

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	public static class Extra {
		public static final String IMAGES = "IMAGES";
		public static final String IMAGE_POSITION = "IMAGE_POSITION";
	} 
	
}
 