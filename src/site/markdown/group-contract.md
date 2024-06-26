## group-contract
### Motivation
In most object-oriented programming languages, there are constructs to define a "contract", that concrete implementations of complex
types will implement. In Java, for example, there is the `interface`, in Scala there are "traits", and so on.
The XML Schema Definition Language (XSD) in contrast, has no explicit construct to ensure a complex type meets a
pre-defined contract. There are, however, the `group` and `attributeGroup` elements, that could be considered
a way to achieve just that: A complexType that uses a `<group>` or an `<attributeGroup>` will expose the
properties defined in these group definitions. Looking at it that way, you could say that the `complexType`
"implements" the contract defined by the `group` or `attributeGroup`.



### Function
The group-contract plugin now tries to model that case in the generated source code. For every `group`and `attributeGroup`
definition in the XSD model (or in any upstream XSD model that is included via the "episode" mechanism, for that matter),
it generates an `interface` definition with all the getter, and optionally setter, methods of the properties defined via
the `group` or `attributeGroup` definition.

Then, it declares every class that was generated from a `complexType` that uses the `group` or `attributeGroup` as implementing
just that interface. This way, all classes generated from XSD complexTypes that use the same group definitions, will
share a common contract and can be treated in a common way by client code.

If the "fluent-builder" plugin is also activated, the interface definition can optionally include the declarations of the "with..."
and "add..." methods of the generated builder class as a nested interface declaration, so you can even rely on a common
"builder" contract for classes using the same `group` and `attributeGroup` definitions.

For example, you may wish to add "XLink" functionality to your generated classes. If the group-contract plugin is
activated, you can define a complexType in XSD that supports the "simple" attributes by adding to its XSD definition:

``` xml
<complexType name="some-type">
	.... (model group of the type...)
	<attributeGroup ref="xlink:simpleAttrs"/>
</complexType>
```

Which will generate a class something like:

``` java
public class SomeType implements SimpleAttrs {
...
```

And an interface definition like:

``` java
public interface SimpleAttrs {
	String getHref();
	void setHref(final String value);
	// ... more properties ...

	// this part is generated only if fluent-builder is also active
	interface BuildSupport<TParentBuilder >{
            public SimpleAttrs.BuildSupport<TParentBuilder> withHref(final String href);
            //... more properties ...
	}
}
```

Similar effects could be achieved by subclassing complexTypes, but since there is no multiple inheritance, inheritance
hierarchies can get overly complex this way, and inheritance is less flexible than interface implementations.

**Note:** The group-contract plugin supports JAXB modular compilation, i.e. the "episode" mechanism implemented
in the JAXB reference impplementation.
However, due to the lack of extensibility of the current default episode data structures and processing, this plugin
has to manage its own "episode" file. There are two command line options to control the  names of the "upstream" episode
file, i.e. the file name the plugin should look for when using other modules, and the "downstream" file, i.e. the file
name that should be generated for use by other modules.



### Usage
#### -Xgroup-contract

#### Options

##### -group-contract.declareSetters=`{y|n}` (y)
Also generate property setter methods in interface declarations.


##### -group-contract.declareBuilderInterface=`{y|n}` (y)
If the "fluent builder plugin" (-Xfluent-builder) is also active, generate interface for the internal builder classes as well.


##### -group-contract.supportInterfaceNameSuffix=`<string>` (Lifecycle)
If this is set, methods that could cause type conflicts when two generated interfaces are used together as type parameter bounds, will be put in another interface named the same as the original interface, but with the suffix specified here.


##### -group-contract.upstreamEpisodeFile=`<string>` (META-INF/jaxb-interfaces.episode)
Use the given resource file to obtain information about interfaces defined in an upstream module (refer to "-episode" option of XJC).


##### -group-contract.downstreamEpisodeFile=`<string>` (META-INF/jaxb-interfaces.episode)
Generate "episode" file for downstream modules in the given resource location.

