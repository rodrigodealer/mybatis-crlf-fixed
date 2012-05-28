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
package com.testdomain;

/*
 * Created by IntelliJ IDEA.
 * User: cbegin
 * Date: May 14, 2005
 * Time: 1:39:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class PrivateBook extends Document {

  private Integer pages;

  private Integer getPages() {
    return pages;
  }

  private void setPages(Integer pages) {
    this.pages = pages;
  }

}
