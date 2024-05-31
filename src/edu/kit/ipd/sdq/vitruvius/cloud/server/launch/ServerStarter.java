package edu.kit.ipd.sdq.vitruvius.cloud.server.launch;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

import edu.kit.ipd.sdq.metamodels.families.FamiliesPackage;
import edu.kit.ipd.sdq.metamodels.persons.PersonsPackage;
import tools.vitruv.change.atomic.AtomicPackage;
import tools.vitruv.change.interaction.UserInteractionFactory;
import tools.vitruv.change.propagation.ChangePropagationSpecification;
import tools.vitruv.dsls.demo.familiespersons.families2persons.FamiliesToPersonsChangePropagationSpecification;
import tools.vitruv.dsls.demo.familiespersons.persons2families.PersonsToFamiliesChangePropagationSpecification;
import tools.vitruv.framework.remote.server.VitruvServer;
import tools.vitruv.framework.views.ViewType;
import tools.vitruv.framework.views.ViewTypeFactory;
import tools.vitruv.framework.vsum.VirtualModelBuilder;
import tools.vitruv.testutils.DefaultVirtualModelBasedTestView;
import tools.vitruv.testutils.VirtualModelBasedTestView;
import tools.vitruv.testutils.views.UriMode;

public class ServerStarter {
	private static Path path = Path.of("/cloud-vsum");
	
	private final static String familyViewType = "families";
	private final static String personsViewType = "persons";

	public ServerStarter() {
		
		EPackage.Registry.INSTANCE.put(FamiliesPackage.eNS_URI, FamiliesPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(PersonsPackage.eNS_URI, PersonsPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(tools.vitruv.dsls.reactions.runtime.correspondence.CorrespondencePackage.eNS_URI,
				tools.vitruv.dsls.reactions.runtime.correspondence.CorrespondencePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(AtomicPackage.eNS_URI, AtomicPackage.eINSTANCE);
		this.testView = new DefaultVirtualModelBasedTestView(path, path, getChangePropagationSpecifications(),
				UriMode.FILE_URIS);
	}

//	 TestView testView= ChangePublishingTestView.createDefaultChangePublishingTestView(path, getChangePropagationSpecifications());

	final VirtualModelBasedTestView testView;

	private static Iterable<ChangePropagationSpecification> getChangePropagationSpecifications() {
		FamiliesToPersonsChangePropagationSpecification _familiesToPersonsChangePropagationSpecification = new FamiliesToPersonsChangePropagationSpecification();
		PersonsToFamiliesChangePropagationSpecification _personsToFamiliesChangePropagationSpecification = new PersonsToFamiliesChangePropagationSpecification();
		return Collections.<ChangePropagationSpecification>unmodifiableList(CollectionLiterals
				.<ChangePropagationSpecification>newArrayList(_familiesToPersonsChangePropagationSpecification,
						_personsToFamiliesChangePropagationSpecification));
	}

	public void startServer() throws IOException {

		VitruvServer server;
		var identityViewType = ViewTypeFactory.createIdentityMappingViewType(familyViewType);
		var dummyViewType1 = ViewTypeFactory.createIdentityMappingViewType(personsViewType);
		//var dummyViewType2 = ViewTypeFactory.createIdentityMappingViewType("dummy 2");
		List<ViewType<?>> listviewTypes = List.of(identityViewType, dummyViewType1);
		server = new VitruvServer(() -> {
			var vsumBuilder = new VirtualModelBuilder();
			vsumBuilder.withStorageFolder(path);
			vsumBuilder.withChangePropagationSpecifications(getChangePropagationSpecifications());
			vsumBuilder.withUserInteractor(UserInteractionFactory.instance.createUserInteractor(
					UserInteractionFactory.instance.createPredefinedInteractionResultProvider(null)));
			vsumBuilder.withViewTypes(listviewTypes);
			var model = vsumBuilder.buildAndInitialize();
			model.createSelector(identityViewType);
			return model;
		}, 8069);

//ModelInitializer.createFamiliesModel(testView);

		server.start();

	}

}
