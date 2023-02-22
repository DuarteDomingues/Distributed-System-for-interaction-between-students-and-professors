package util;

public class Expressions {

	// client sends this to server to inform it has tried to login
	public static final String login = "<login>";
	public static final String loginEnd = "</login>";

	// server send user info to client, based on client id
	public static final String userInfo = "<user";
	public static final String userInfoEnd = "</user>";

	// server send user info about the turmas and ucs that he's in
	public static final String turmaInfo = "<ucInfo>";
	public static final String turmaInfoEnd = "</ucInfo>";

	// client sends info to server requesting chat , based on the id of a turma and
	// unidade curricular
	public static final String requestChatTurma = "<chatInfoTurma>";
	public static final String requestChatTurmaEnd = "</chatInfoTurma>";

	// client sends info to server requesting chat , based on the id of unidade
	// curricular
	public static final String requestChatUc = "<chatInfoUc>";
	public static final String requestChatUcEnd = "</chatInfoUc>";

	// check if chat exists based on uc and class
	public static final String checkChats = "<checkChat>";
	public static final String checkChatsEnd = "</checkChat>";
	
	public static final String requestChatGroup = "<chatGroupStudents>";
	public static final String requestChatGroupEnd = "</chatGroupStudents>";


	// send class chat info to user
	public static final String sendClassChat = "<sendChatTurma";
	public static final String sendClassChatEnd = "</sendChatTurma>";

	// send uc chat info to user
	public static final String sendUcChat = "<sendChatUc";
	public static final String sendUcChatEnd = "</sendChatUc>";
	
	public static final String sendGroupChat = "<sendGroupChat";
	public static final String sendGroupChatEnd = "</sendGroupChat>";
	
	

	// send chat message in turma chata
	public static final String sendTurmaMessage = "<sendTurmaMessage>";
	public static final String sendTurmaMessageEnd = "</sendTurmaMessage>";

	// Send uc message in chat
	public static final String sendUcMessage = "<ucTurmaMessage>";
	public static final String sendUcMessageEnd = "</ucTurmaMessage>";
	
	public static final String sendGroupChatMessage ="<groupChatMessage>";
	public static final String sendGroupChatMessageEnd ="</groupChatMessage>";


	public static final String sendTurmaMessageUser = "<sendTurmaMessageUser>";
	public static final String sendTurmaMessageUserEnd = "</sendTurmaMessageUser>";

	public static final String sendUcMessageUser = "<sendUcMessageUser>";
	public static final String sendUcMessageUserEnd = "</sendUcMessageUser>";
	
	public static final String sendGroupMessageUser = "<sendGroupMessageUser>";
	public static final String sendGroupMessageUserEnd = "</sendGroupMessageUser>";

	// back
	public static final String back = "<back>";
	public static final String backEnd = "</back>";

	public static final String updateUserInfo = "<UpdateUser>";
	public static final String updateUserInfoEnd = "</UpdateUser>";

	// aluno create group
	public static final String alunoCreateGrupo = "<AlunoCreateGroup>";
	public static final String alunoCreateGrupoEnd = "</AlunoCreateGroup>";

	public static final String sendAlunos = "<sendAlunos>";
	public static final String sendAlunosEnd = "</sendAlunos>";
	
	//send Student Create Group Message
	public static final String sendStudentCreateGroup = "<createStudentChat>";
	public static final String sendStudentCreateGroupEnd = "</createStudentChat>";
	
	//professor create uc or turma group
	public static final String professorCreateGroup = "<createProfessorChat>";
	public static final String professorCreateGroupEnd = "</createProfessorChat>";

	



	

}
