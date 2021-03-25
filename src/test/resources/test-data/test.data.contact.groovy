import com.neps.groovydatamapper.model.Contact

table(Contact, ['appleId','firstName','lastName','dob','income','street','city','state','zip','alias']){
	contact1 = row 'nepolian_louis@apple.com', 'Nepolian', 'Louis', '03/11/1989','8000','9653 XYSD PHS ST','NSJSDKAD', 'NM', '87111', 'nlouis'
	contact2 = row 'ipatel@apple.com', 'Ilyas', 'Patel', '03/11/1979','8000','9653 XYSD PHS ST','NSJSDKAD', 'NM', '87111', 'ipatel'
}