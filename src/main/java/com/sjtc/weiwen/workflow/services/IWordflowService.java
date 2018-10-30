package com.sjtc.weiwen.workflow.services;

import org.activiti.engine.runtime.ProcessInstance;

public interface IWordflowService {

	ProcessInstance startProcessInstanceByKey();
}
