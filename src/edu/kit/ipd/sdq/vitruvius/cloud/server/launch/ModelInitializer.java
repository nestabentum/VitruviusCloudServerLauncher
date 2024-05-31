package edu.kit.ipd.sdq.vitruvius.cloud.server.launch;

import java.nio.file.Path;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import edu.kit.ipd.sdq.metamodels.families.FamiliesFactory;
import edu.kit.ipd.sdq.metamodels.families.Family;
import edu.kit.ipd.sdq.metamodels.families.FamilyRegister;
import edu.kit.ipd.sdq.metamodels.families.Member;
import edu.kit.ipd.sdq.metamodels.persons.PersonsFactory;
import tools.vitruv.testutils.views.TestView;

public class ModelInitializer {
	private static final Path FAMILIES_MODEL = Path.of("model/families1.families");

	private static final String FIRST_DAD_1 = "Anton";

	private static final String FIRST_MOM_1 = "Berta";

	private static final String FIRST_SON_1 = "Chris";

	private static final String FIRST_DAU_1 = "Daria";

	private static final String LAST_NAME = "Herbertus";

	public static void createFamiliesModel(TestView testView) {
		insertRegister(testView);
	}

	private static long id_counter = 0;

	public static void insertRegister(TestView testView) {
		Resource resource = testView.resourceAt(FAMILIES_MODEL);
		final Consumer<Resource> _function = (Resource it) -> {
			EList<EObject> _contents = it.getContents();

			// Family
			FamilyRegister familyRegister = FamiliesFactory.eINSTANCE.createFamilyRegister();
			familyRegister.setId(getId());
			Family family = FamiliesFactory.eINSTANCE.createFamily();
			PersonsFactory.eINSTANCE.createFemale();
			family.setLastName(LAST_NAME);
			family.setFather(createMember(FIRST_DAD_1));
			family.setMother(createMember(FIRST_MOM_1));
			family.getDaughters().add(createMember(FIRST_DAU_1));
			family.getSons().add(createMember(FIRST_SON_1));
			familyRegister.getFamilies().add(family);
			_contents.add(familyRegister);

			// Persons
		//	PersonRegister personRegister = PersonsFactory.eINSTANCE.createPersonRegister();
		//	personRegister.setId(getId());
        //
		//	Person mother = PersonsFactory.eINSTANCE.createFemale();
		//	mother.setFullName(FIRST_MOM_1 + " " + LAST_NAME);
		//	mother.setBirthday(Date.from(Instant.now()));
        //
		//	Person father = PersonsFactory.eINSTANCE.createMale();
		//	mother.setFullName(FIRST_DAD_1 + " " + LAST_NAME);
		//	father.setBirthday(Date.from(Instant.now()));
		//	
		//	
		//	personRegister.getPersons().add(mother);
		};

		testView.propagate(resource, _function);
	}

	private static Member createMember(String name) {
		Member member = FamiliesFactory.eINSTANCE.createMember();
		member.setFirstName(name);
		return member;
	}

	private static String getId() {
		return id_counter++ + "";
	}
}
