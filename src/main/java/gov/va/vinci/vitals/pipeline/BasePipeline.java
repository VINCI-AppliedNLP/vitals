package gov.va.vinci.vitals.pipeline;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.pipeline.PipelineInterface;

import java.io.Serializable;
import java.util.HashMap;

public abstract class BasePipeline  implements PipelineInterface, Serializable{
    protected LeoAEDescriptor pipeline = null;
    protected LeoTypeSystemDescription description = null;

    public BasePipeline(HashMap argsMap) throws NoSuchMethodException {
        super();
        System.out.println(this.getClass().getName());
        this.getClass().getConstructor(HashMap.class);
    }

    public BasePipeline() {
        try {
            this.getClass().getConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public LeoTypeSystemDescription getLeoTypeSystemDescription() {
        if (description == null) {
            return defineTypeSystem();
        } else
            return description;
    }


    public LeoAEDescriptor getPipeline() {
        return pipeline;
    }

    protected abstract LeoTypeSystemDescription defineTypeSystem();
}
