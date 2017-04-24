package org.mybatis.generator.extend;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;

public interface MyPlugin {
	/**
	 * 是否添加xml的注释
	 */
	boolean IS_ADD_XML_COMMENT = false;

	/**
	 * 是否使用自定义的文件注释
	 */
	boolean IS_MY_FILE_COMMENT = true;
	/**
	 * 是否使用自定义的类主食
	 */
	boolean IS_MY_CLASS_COMMENT = true;

	/**
	 * 是否使用自定义的属性注释
	 */
	boolean IS_MY_FIELD_COMMENT = true;
	/**
	 * 是否使用自定义的Get方法注释
	 */
	boolean IS_MY_GETTER_COMMENT = true;

	/**
	 * 是否使用自定义的Set方法注释
	 */
	boolean IS_MY_SETTER_COMMENT = true;

	/**
	 * 
	 * 自定义文件类
	 *
	 * @param compilationUnit
	 */
	static void addFileDoc(CompilationUnit compilationUnit) {
		if (IS_MY_FIELD_COMMENT) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			compilationUnit.addFileCommentLine("/*");
			compilationUnit.addFileCommentLine(" * " + compilationUnit.getType().getShortName() + ".java");
			compilationUnit.addFileCommentLine(" * Copyright(C) 2017 飞羽company ");
			compilationUnit.addFileCommentLine(" * createTime : " + sdf.format(new Date()));
			compilationUnit.addFileCommentLine(" */");
		}
	}

	static void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (IS_MY_CLASS_COMMENT) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			innerClass.addJavaDocLine("/*");
			innerClass.addJavaDocLine(" * " + "备注");
			innerClass.addJavaDocLine(" * ");
			innerClass.addJavaDocLine(" * @author feiyu127");
			innerClass.addJavaDocLine(" * @version 1.0 " + sdf.format(new Date()));
			innerClass.addJavaDocLine(" */");
		}
	}

	static boolean addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (IS_MY_FIELD_COMMENT) {
			field.addJavaDocLine("/*");
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			field.addJavaDocLine(" */");
		}
		return IS_MY_FIELD_COMMENT;
	}

	static boolean addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (IS_MY_GETTER_COMMENT) {
			method.addJavaDocLine("/*");
			method.addJavaDocLine(" * 获取 " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
		return IS_MY_GETTER_COMMENT;
	}

	static boolean addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (IS_MY_SETTER_COMMENT) {
			method.addJavaDocLine("/*");
			method.addJavaDocLine(" * 设置 " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
		return IS_MY_SETTER_COMMENT;
	}
}
