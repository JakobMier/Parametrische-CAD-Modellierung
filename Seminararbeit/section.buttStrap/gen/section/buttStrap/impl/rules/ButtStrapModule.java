package section.buttStrap.impl.rules;

import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.SequentialUnit;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.Node;
import de.iils.dc43.core.graph.Edge;
import org.eclipse.emf.henshin.model.MappingList;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.ParameterKind;
import org.eclipse.emf.henshin.model.AttributeCondition;
import de.iils.dc43.core.graph.impl.GraphPackageImpl;
import org.eclipse.emf.henshin.model.Annotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcorePackage;
import dc43Utilities.impl.Dc43UtilitiesPackageImpl;
import java.util.Map;
import java.util.HashMap;
import tec.uom.se.NumberQuantity;
import de.iils.dc43.core.ecore.IRuleProvider;
import java.lang.reflect.Method;
import java.util.Comparator;
import de.iils.dc43.core.exceptions.ExceptionUtil;
import section.buttStrap.impl.ButtStrapPackageImpl;
// Generated by: DC43RuleCodeGenerator2023-01-04T04:17:08.473+0100
@SuppressWarnings("all")
public final class ButtStrapModule implements IRuleProvider<Module> {
	public static final ButtStrapModule INSTANCE = init();
	private static boolean isInited = false;
	private Module module;
	private ButtStrapModule() {};
	public static ButtStrapModule init() {
		if(isInited) {
			return INSTANCE;
		}
		ButtStrapModule self = new ButtStrapModule();
		self.initContents();
		isInited = true;
		return self;
	}

	public Module getRules() {
		return module;
	}
	private void initContents() {
		module = HenshinFactory.eINSTANCE.createModule();
		module.setName("ButtStrap");

		Comparator<Method> methodComparator = Comparator.comparing(Method::getName);
		Method[] methods = getClass().getDeclaredMethods();
		java.util.Arrays.asList(methods).stream().filter(m -> m.getName().startsWith("create"))
			.sorted(methodComparator.reversed()).forEach(m -> {
				try { m.invoke(this); }
				catch(Exception e) { ExceptionUtil.sneakyThrow(e); }
			});
	}

	//RULES

}