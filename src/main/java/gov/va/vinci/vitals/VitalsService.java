package gov.va.vinci.vitals;

/*
 * #%L
 * Leo Examples
 * %%
 * Copyright (C) 2010 - 2014 Department of Veterans Affairs
 * %%
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
 * #L%
 */

import gov.va.vinci.leo.Service;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.vitals.pipelines.Numbers;

/**
 * Created by ryancornia on 10/7/14.
 * Updated by ovpatterson on 11/10/2014
 */
public class VitalsService {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Service service = null;

        try {
            service = new gov.va.vinci.leo.Service();

            /**
             * The UIMA AS Broker url that is coordinating requests.
             */
            service.setBrokerURL("tcp://localhost:61616");

            /**
             * The endpoint name of the UIMA AS service to use for processing. The service will be
             * registered with the broker as this service name. Clients use the broker/service combination
             * to connect to this service.
             */
            service.setEndpoint("leoExample");

            /** Tell the service to persist the descriptors that are generated, deletes them by default **/
            service.setDeleteOnExit(false);

            /* Create an aggregate of the components. */
            LeoAEDescriptor aggregate =  new Numbers().getPipeline();
            aggregate.setIsAsync(true);
            aggregate.setNumberOfInstances(1);

            /* Deploy the service. */
            service.deploy(aggregate);

            System.out.println("Deployment: " + service.getDeploymentDescriptorFile());
            System.out.println("Aggregate: " + service.getAggregateDescriptorFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
