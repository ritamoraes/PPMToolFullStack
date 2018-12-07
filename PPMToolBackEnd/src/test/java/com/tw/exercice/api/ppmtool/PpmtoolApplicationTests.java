package com.tw.exercice.api.ppmtool;

import com.tw.exercice.api.ppmtool.domain.Project;
import com.tw.exercice.api.ppmtool.services.ProjectService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PpmtoolApplicationTests {

	Project project;

	@Before
	public void setUp() throws Exception {
		project = new Project();
		project.setProjectIdentifier("PRID1");
		project.setDescription("Delete test project");
		project.setProjectName("Project that will be deleted");
		service.saveOrUpdateProject(project);
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Autowired
	ProjectService service;

	@Test
	public void testDeleteProject() {

		service.deleteProjectByIdentifier(project.getProjectIdentifier());
		exception.expectMessage("Project ID 'PRID1' does not exists");
		service.findProjectByIdentifier(project.getProjectIdentifier());
	}

	@Test
	public void updateProject() {

		project.setProjectName("Update Test Project");
		project.setDescription("Project that will be updated");

		service.saveOrUpdateProject(project);

		Project updatedProject = service.findProjectByIdentifier(project.getProjectIdentifier());

		assertTrue(updatedProject.getProjectName().equals(project.getProjectName()) &&
				updatedProject.getDescription().equals(project.getDescription()));

	}
}
