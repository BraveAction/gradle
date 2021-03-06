/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.language.base.sources;

import org.gradle.api.Incubating;
import org.gradle.api.internal.file.SourceDirectorySetFactory;
import org.gradle.internal.reflect.DirectInstantiator;
import org.gradle.internal.reflect.ObjectInstantiationException;
import org.gradle.language.base.LanguageSourceSet;
import org.gradle.language.base.internal.AbstractLanguageSourceSet;
import org.gradle.platform.base.ModelInstantiationException;
import org.gradle.platform.base.internal.ComponentSpecIdentifier;

/**
 * Base class that may be used for custom {@link LanguageSourceSet} implementations. However, it is generally better to use an
 * interface annotated with {@link org.gradle.model.Managed} and not use an implementation class at all.
 */
@Incubating
public class BaseLanguageSourceSet extends AbstractLanguageSourceSet {
    // This is here as a convenience for subclasses to create additional SourceDirectorySets
    protected final SourceDirectorySetFactory sourceDirectorySetFactory;

    private static ThreadLocal<SourceSetInfo> nextSourceSetInfo = new ThreadLocal<SourceSetInfo>();

    public static <T extends LanguageSourceSet> T create(Class<? extends LanguageSourceSet> publicType, Class<T> implementationType, ComponentSpecIdentifier componentId, SourceDirectorySetFactory sourceDirectorySetFactory) {
        nextSourceSetInfo.set(new SourceSetInfo(componentId, publicType, sourceDirectorySetFactory));
        try {
            try {
                return DirectInstantiator.INSTANCE.newInstance(implementationType);
            } catch (ObjectInstantiationException e) {
                throw new ModelInstantiationException(String.format("Could not create LanguageSourceSet of type %s", publicType.getSimpleName()), e.getCause());
            }
        } finally {
            nextSourceSetInfo.set(null);
        }
    }

    public BaseLanguageSourceSet() {
        this(nextSourceSetInfo.get());
    }

    private BaseLanguageSourceSet(SourceSetInfo info) {
        super(validate(info).identifier, info.publicType, info.sourceDirectorySetFactory.create("source"));
        this.sourceDirectorySetFactory = info.sourceDirectorySetFactory;
    }

    private static SourceSetInfo validate(SourceSetInfo info) {
        if (info == null) {
            throw new ModelInstantiationException("Direct instantiation of a BaseLanguageSourceSet is not permitted. Use a @ComponentType rule instead.");
        }
        return info;
    }

    private static class SourceSetInfo {
        private final ComponentSpecIdentifier identifier;
        private final Class<? extends LanguageSourceSet> publicType;
        final SourceDirectorySetFactory sourceDirectorySetFactory;

        private SourceSetInfo(ComponentSpecIdentifier identifier, Class<? extends LanguageSourceSet> publicType, SourceDirectorySetFactory sourceDirectorySetFactory) {
            this.identifier = identifier;
            this.publicType = publicType;
            this.sourceDirectorySetFactory = sourceDirectorySetFactory;
        }
    }
}
