/*
 * Copyright 2016 Cask Data, Inc.
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

package co.cask.cdap.security.authorization.sentry.model;

import co.cask.cdap.proto.ProgramType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for different {@link Authorizable}
 */
public class TestAuthorizable {

  @Test
  public void testAuth() throws Exception {

    String name = "test";

    Instance instance = new Instance(name);
    Assert.assertEquals(name, instance.getName());
    Assert.assertEquals(Authorizable.AuthorizableType.INSTANCE, instance.getAuthzType());

    Namespace namespace = new Namespace(name);
    Assert.assertEquals(name, namespace.getName());
    Assert.assertEquals(Authorizable.AuthorizableType.NAMESPACE, namespace.getAuthzType());

    Artifact artifact = new Artifact("art", "1");
    Assert.assertEquals("art", artifact.getArtifactName());
    Assert.assertEquals("1", artifact.getArtifactVersion());
    Assert.assertEquals(Authorizable.AuthorizableType.ARTIFACT, artifact.getAuthzType());

    Application application = new Application(name);
    Assert.assertEquals(name, application.getName());
    Assert.assertEquals(Authorizable.AuthorizableType.APPLICATION, application.getAuthzType());

    Program program = new Program(ProgramType.FLOW, "flow1");
    Assert.assertEquals(ProgramType.FLOW, program.getProgramType());
    Assert.assertEquals("flow1", program.getProgramName());
    Assert.assertEquals(Authorizable.AuthorizableType.PROGRAM, program.getAuthzType());

    Stream stream = new Stream(name);
    Assert.assertEquals(name, stream.getName());
    Assert.assertEquals(Authorizable.AuthorizableType.STREAM, stream.getAuthzType());

    Dataset dataset = new Dataset(name);
    Assert.assertEquals(name, dataset.getName());
    Assert.assertEquals(Authorizable.AuthorizableType.DATASET, dataset.getAuthzType());
  }
}
