package implemention;

import interfaces.procedure;
import interfaces.procedureFactory;

public class procFactoryImplemention implements procedureFactory{

	@Override
	public procedure createProcedure() {
		// TODO Auto-generated method stub
		return new procImplemention();
	}

}
