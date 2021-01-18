/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shortthirdman.core.database;

/**
 * Hibernate dialects classes
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public interface HibernateDialects {
	
	String ORACLE = "org.hibernate.dialect.OracleDialect";

	String ORACLE9I = "org.hibernate.dialect.Oracle9iDialect";

	String ORACLE10G = "org.hibernate.dialect.Oracle10gDialect";

	String MYSQL = "org.hibernate.dialect.MySQLDialect";

	String MYSQLINNODB = "org.hibernate.dialect.MySQLInnoDBDialect";

	String MYSQLMYISAM = "org.hibernate.dialect.MySQLMyISAMDialect";

	String DB2 = "org.hibernate.dialect.DB2Dialect";

	String DB2AS400 = "org.hibernate.dialect.DB2400Dialect";

	String DB2OS390 = "org.hibernate.dialect.DB2390Dialect";

	String MSSQLSERVER = "org.hibernate.dialect.SQLServerDialect";

	String SYBASE = "org.hibernate.dialect.SybaseDialect";

	String SYBASEANYWHER = "org.hibernate.dialect.SybaseAnywhereDialect";

	String POSTGRESQL = "org.hibernate.dialect.PostgreSQLDialect";

	String SAPDB = "org.hibernate.dialect.SAPDBDialect";

	String INFORMIX = "org.hibernate.dialect.InformixDialect";

	String HYPERSONICSQL = "org.hibernate.dialect.HSQLDialect";

	String INGRES = "org.hibernate.dialect.IngresDialect";

	String PROGRESS = "org.hibernate.dialect.ProgressDialect";

	String MCKOISQL = "org.hibernate.dialect.MckoiDialect";

	String INTERBASE = "org.hibernate.dialect.InterbaseDialect";

	String POINTBASE = "org.hibernate.dialect.PointbaseDialect";

	String FRONTBASE = "org.hibernate.dialect.FrontbaseDialect";

	String FIREBIRD = "org.hibernate.dialect.FirebirdDialect";
}
