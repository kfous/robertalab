package de.fhg.iais.roberta.ast.syntax.sensors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;

import de.fhg.iais.roberta.ast.syntax.Phrase;
import de.fhg.iais.roberta.ast.transformer.JaxbTransformer;
import de.fhg.iais.roberta.blockly.generated.Project;

public class InfraredSensorTest {
    @Test
    public void setInfrared() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputSource src = new InputSource(Math.class.getResourceAsStream("/ast/sensors/sensor_setInfrared.xml"));
        Project project = (Project) jaxbUnmarshaller.unmarshal(src);

        JaxbTransformer transformer = new JaxbTransformer();
        transformer.projectToAST(project);

        String a = "hal.setInfraredSensorMode(S4, DISTANCE);";

        Assert.assertEquals(a, generate(transformer.getProject().get(0).get(0)));

    }

    @Test
    public void getInfraredModeName() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputSource src = new InputSource(Math.class.getResourceAsStream("/ast/sensors/sensor_getModeInfrared.xml"));
        Project project = (Project) jaxbUnmarshaller.unmarshal(src);

        JaxbTransformer transformer = new JaxbTransformer();
        transformer.projectToAST(project);

        String a = "hal.getInfraredSensorModeName(S4)";

        Assert.assertEquals(a, generate(transformer.getProject().get(0).get(0)));
    }

    @Test
    public void getSampleInfrared() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputSource src = new InputSource(Math.class.getResourceAsStream("/ast/sensors/sensor_getSampleInfrared.xml"));
        Project project = (Project) jaxbUnmarshaller.unmarshal(src);

        JaxbTransformer transformer = new JaxbTransformer();
        transformer.projectToAST(project);

        String a = "hal.getInfraredSensorValue(S4)";

        Assert.assertEquals(a, generate(transformer.getProject().get(0).get(0)));
    }

    private String generate(Phrase p) {
        StringBuilder sb = new StringBuilder();
        p.generateJava(sb, 0);
        System.out.println(sb.toString());
        return sb.toString();
    }
}
