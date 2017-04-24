package org.mybatis.generator.extend;

import org.mybatis.generator.api.ShellRunner;

public class MyTestRun {
	public static void main(String[] args) {
		String config = MyTestRun.class.getClassLoader().getResource("org/generatorConfig.xml").toString();
		System.out.println(config);
		System.out.println("a");
		String[] arg = {"-configfile", config.substring(6), "-overwrite"};
		System.out.println("b");
		ShellRunner.main(arg);
		
	}
}
