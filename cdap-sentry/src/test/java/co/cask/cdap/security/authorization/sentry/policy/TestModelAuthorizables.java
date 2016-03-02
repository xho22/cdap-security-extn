/*
 *
 * Copyright © 2016 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.cdap.security.authorization.sentry.policy;

import co.cask.cdap.security.authorization.sentry.model.Instance;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link ModelAuthorizables}
 */
public class TestModelAuthorizables {

  @Test
  public void testInstance() throws Exception {
    Instance instance = (Instance) ModelAuthorizables.from("instance=cdap1");
    Assert.assertEquals("cdap1", instance.getName());
  }

  @Test
  public void testInvalidAuthorizable() throws Exception {
    Assert.assertNull(ModelAuthorizables.from("hello=world"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoKV() throws Exception {
    testFailure("noKey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyKey() throws Exception {
    testFailure("=emptyKey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyValue() throws Exception {
    testFailure("emptyValue=");
  }

  private void testFailure(String modelAuthorizables) throws Exception {
    ModelAuthorizables.from(modelAuthorizables);
  }
}
