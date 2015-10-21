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

package com.kscs.util.jaxb;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * @author Mirko Klemm 2015-10-21
 */
public abstract class AnonymousIndirectCollectionPropertyInfo<I> extends PropertyInfo<I,Object> {
	protected AnonymousIndirectCollectionPropertyInfo(final String propertyName, final Class<I> declaringClass, final Class<Object> propertyType, final boolean collection, final Object defaultValue, final QName schemaName, final QName schemaType, final boolean attribute) {
		super(propertyName, declaringClass, propertyType, collection, defaultValue, schemaName, schemaType, attribute);
	}

	@Override
	public abstract List<JAXBElement<?>> get(final I i) ;

	public abstract void set(final I instance, final List<JAXBElement<?>> values);

}
