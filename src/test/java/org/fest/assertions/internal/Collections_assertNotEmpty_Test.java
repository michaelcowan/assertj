/*
 * Created on Sep 26, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions.internal;

import static java.util.Collections.emptyList;
import static org.fest.assertions.error.WhenEmptyErrorFactory.errorWhenEmpty;
import static org.fest.assertions.test.Exceptions.assertionFailingOnPurpose;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.unexpectedNull;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.fest.assertions.core.*;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Collections#assertNotEmpty(AssertionInfo, Collection)}</code>.
 *
 * @author Alex Ruiz
 */
public class Collections_assertNotEmpty_Test {

  private static WritableAssertionInfo info;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Collections collections;

  @BeforeClass public static void setUpOnce() {
    info = new WritableAssertionInfo();
  }

  @Before public void setUp() {
    failures = mock(Failures.class);
    collections = new Collections(failures);
  }

  @Test public void should_pass_if_actual_is_not_empty() {
    collections.assertNotEmpty(info, list("Luke"));
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(unexpectedNull());
    collections.assertNotEmpty(info, null);
  }

  @Test public void should_fail_if_actual_is_empty() {
    AssertionError expectedError = assertionFailingOnPurpose();
    Collection<String> actual = emptyList();
    when(failures.failure(info, errorWhenEmpty())).thenReturn(expectedError);
    thrown.expect(expectedError);
    collections.assertNotEmpty(info, actual);
  }
}
