/*
 * MIT License
 *
 * Copyright (c) 2014 Klemm Software Consulting, Mirko Klemm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kscs.util.plugins.xjc.outline;

import java.util.List;

import javax.xml.namespace.QName;

import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.xml.bind.v2.model.core.TypeInfo;

/**
 * @author Mirko Klemm 2015-01-28
 */
public interface PropertyOutline {
	String getBaseName();
	String getFieldName();
	JType getRawType();
	JType getElementType();
	JFieldVar getFieldVar();
	boolean hasGetter();
	boolean isCollection();
	List<TagRef> getChoiceProperties();

	class TagRef {
		private final QName tagName;
		private final TypeInfo<NType,NClass> typeInfo;

		public TagRef(final QName tagName, final TypeInfo<NType, NClass> typeInfo) {
			this.tagName = tagName;
			this.typeInfo = typeInfo;
		}

		public QName getTagName() {
			return this.tagName;
		}

		public TypeInfo<NType, NClass> getTypeInfo() {
			return this.typeInfo;
		}
	}
}
