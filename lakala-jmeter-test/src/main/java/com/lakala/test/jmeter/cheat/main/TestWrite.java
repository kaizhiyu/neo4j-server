package com.lakala.test.jmeter.cheat.main;

import com.lakala.test.jmeter.cheat.logic.ApplyInfoRelationServiceImpl;
import com.lakala.test.jmeter.cheat.logic.MobileRelationServiceImpl;
import com.lakala.test.jmeter.cheat.repository.Neo4jUtilServiceImpl;

public class TestWrite {

	public static void main(String[] args) {

		String ip="192.168.0.33";
		Neo4jUtilServiceImpl.url="bolt://"+ip+":7687";
        MobileRelationServiceImpl savemobile=new MobileRelationServiceImpl("2");
        savemobile.saveMobileToNeo4j();
        ApplyInfoRelationServiceImpl saveapply=new ApplyInfoRelationServiceImpl("2");
        saveapply.saveNeo4j();
	}
}
