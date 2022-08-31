package org.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.commonj.WorkManagerTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.*;

@Controller
public class HelloController {

	@Autowired
	private WorkManagerTaskExecutor executor;
//	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(40);


	@RequestMapping(value = "/testThreads/{delay}/{wait}", method = RequestMethod.GET)
	public @ResponseBody String holis(@PathVariable String delay,@PathVariable String wait) {

		String result = "Termine";

		Future<String> submit = executor.submit(new MyTask(delay));

		try {
			Long waiting = new Long(wait);
			result = submit.get(waiting, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			result = "Sali por interrupted exception";
		} catch (ExecutionException e) {
			result = "Sali por runtime exception";
		} catch (TimeoutException e) {
			result = "Sali por timeout";
		}finally {
			submit.cancel(true);
		}

		result += "  Esta terminado: "+ submit.isDone();
		result += "  Esta cancelado: "+ submit.isCancelled();


		return result;

	}

	@RequestMapping(value = "/testThreadsWithoutCancel/{delay}/{wait}", method = RequestMethod.GET)
	public @ResponseBody String chau(@PathVariable String delay,@PathVariable String wait) {

		String result = "Termine";

		Future<String> submit = executor.submit(new MyTask(delay));

		try {
			Long waiting = new Long(wait);
			result = submit.get(waiting, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			result = "Sali por interrupted exception";
		} catch (ExecutionException e) {
			result = "Sali por runtime exception";
		} catch (TimeoutException e) {
			result = "Sali por timeout";
		}finally {
			//submit.cancel(true);
		}

		result += "  Esta terminado: "+ submit.isDone();
		result += "  Esta cancelado: "+ submit.isCancelled();


		return result;

	}


}
