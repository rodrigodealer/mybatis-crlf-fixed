/*
 *    Copyright 2009-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.submitted.selectkey;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class SelectKeyTest {

  protected static SqlSessionFactory sqlSessionFactory;

  @Before
  public void setUp() throws Exception {
    Connection conn = null;

    try {
      Class.forName("org.hsqldb.jdbcDriver");
      conn = DriverManager.getConnection("jdbc:hsqldb:mem:lname", "sa",
          "");

      Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/selectkey/CreateDB.sql");

      ScriptRunner runner = new ScriptRunner(conn);
      runner.setLogWriter(null);
      runner.setErrorLogWriter(null);
      runner.runScript(reader);
      conn.commit();
      reader.close();

      reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/selectkey/MapperConfig.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      reader.close();
      sqlSessionFactory.getConfiguration().addMapper(AnnotatedMapper.class);
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }

  @Test
  public void testSelectKey() throws Exception {
    // this test checks to make sure that we can have select keys with the same
    // insert id in different namespaces
    String resource = "org/apache/ibatis/submitted/selectkey/MapperConfig.xml";
    Reader reader = Resources.getResourceAsReader(resource);
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlMapper = builder.build(reader);
    assertNotNull(sqlMapper);
  }

  @Test
  public void testInsertTable1() {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    try {
      Map<String, String> parms = new HashMap<String, String>();
      parms.put("name", "Fred");
      int rows = sqlSession.insert("org.apache.ibatis.submitted.selectkey.Table1.insert", parms);
      assertEquals(1, rows);
      assertEquals(11, parms.get("id"));

    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void testInsertTable2() {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    try {
      Map<String, String> parms = new HashMap<String, String>();
      parms.put("name", "Fred");
      int rows = sqlSession.insert("org.apache.ibatis.submitted.selectkey.Table2.insert", parms);
      assertEquals(1, rows);
      assertEquals(22, parms.get("id"));

    } finally {
      sqlSession.close();
    }
  }
  
  @Test
  public void testAnnotatedInsertTable2() {
      SqlSession sqlSession = sqlSessionFactory.openSession();

      try {
        Name name = new Name();
        name.setName("barney");
        AnnotatedMapper mapper = sqlSession.getMapper(AnnotatedMapper.class);
        int rows = mapper.insertTable2(name);
        assertEquals(1, rows);
        assertEquals(22, name.getNameId());
      } finally {
        sqlSession.close();
      }
  }

  @Test
  public void testAnnotatedInsertTable2WithOptions() {
      SqlSession sqlSession = sqlSessionFactory.openSession();

      try {
        Name name = new Name();
        name.setName("barney");
        AnnotatedMapper mapper = sqlSession.getMapper(AnnotatedMapper.class);
        int rows = mapper.insertTable2WithOptions(name);
        assertEquals(1, rows);
        assertEquals(22, name.getNameId());
        assertEquals("barney_fred", name.getGeneratedName());
      } finally {
        sqlSession.close();
      }
  }

  @Test
  public void testAnnotatedInsertTable3() {
      SqlSession sqlSession = sqlSessionFactory.openSession();

      try {
        Name name = new Name();
        name.setName("barney");
        AnnotatedMapper mapper = sqlSession.getMapper(AnnotatedMapper.class);
        int rows = mapper.insertTable3(name);
        assertEquals(1, rows);
        assertEquals(33, name.getNameId());
      } finally {
        sqlSession.close();
      }
  }

  @Test
  public void testAnnotatedInsertTable3_2() {
      SqlSession sqlSession = sqlSessionFactory.openSession();

      try {
        Name name = new Name();
        name.setName("barney");
        AnnotatedMapper mapper = sqlSession.getMapper(AnnotatedMapper.class);
        int rows = mapper.insertTable3_2(name);
        assertEquals(1, rows);
        assertEquals(33, name.getNameId());
      } finally {
        sqlSession.close();
      }
  }
}
