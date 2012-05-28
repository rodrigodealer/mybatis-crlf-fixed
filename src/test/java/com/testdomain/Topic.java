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

import java.io.Serializable;
import java.util.List;

public class Topic implements Serializable {

  private String topicTitle;
  private List descriptionList;

  /*
   * @return Returns the descriptionList.
   */
  public List getDescriptionList() {
    return descriptionList;
  }

  /*
   * @param descriptionList The descriptionList to set.
   */
  public void setDescriptionList(List description) {
    this.descriptionList = description;
  }

  /*
   * @return Returns the topicTitle.
   */
  public String getTopicTitle() {
    return topicTitle;
  }

  /*
   * @param topicTitle The topicTitle to set.
   */
  public void setTopicTitle(String topicTitle) {
    this.topicTitle = topicTitle;
  }
}