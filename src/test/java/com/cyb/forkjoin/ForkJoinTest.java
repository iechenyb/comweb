package com.cyb.forkjoin;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.web.forkjoin.po.Product;
import com.cyb.web.forkjoin.service.ProductListGeneratorService;

/**
 * 作者 : iechenyb<br>
 * 类描述: sping单元测试<br>
 * 创建时间: 2017年7月19日
 * update tb_fj_product set price=10;
 * select count(*) from tb_fj_product where price=12;
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:applicationContext-redis.xml",
		"classpath*:applicationContext-job.xml" })
// @Transactional 已经通过配置文件控制事务，这里就不用再注明事务了。
public class ForkJoinTest {
	Log log = LogFactory.getLog(ForkJoinTest.class);
	@Autowired
	ProductListGeneratorService service;
    public int threadhold = 100;//小于100则执行，大于则拆分两个小任务
    public int number=10;//5万人
	@Before
	public void init() {
		System.out.println("初始化..." + service);
	}

	@After
	public void show() {
	}

	@Test
	public void genProduct() {
		service.saveProBatch(number*10000);
	}

	@Test
	public void conCal() {
		List<Product> products = service.getAll();
		Task task = new Task(products, 0, products.size(), 0.20);
		// 通过无参的类构造器创建一个ForkJoinPool
		ForkJoinPool pool = new ForkJoinPool();
		// 调用execute()方法执行任务
		pool.execute(task);
		// 显示关于线程池演变的信息
		long s = System.currentTimeMillis();
		try {
			try {
				task.get();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
				
			}
		/*	while (!task.isDone()) {
				// System.out.printf("Main: Thread Count: %d\n",
				// pool.getActiveThreadCount());
				// System.out.printf("Main: Thread Steal: %d\n",
				// pool.getStealCount());
				// System.out.printf("Main: Parallelism: %d\n",
				// pool.getParallelism());
				Thread.sleep(50);
			}*/
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		// 关闭线程池
		pool.shutdown();
		if (task.isCompletedNormally()) {
			System.out.println("Main: The process has completed normally.");
		}
		// 确认是否所有的价格都已经改变
		/*for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12)
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
		}*/
		service.updateProduct(products);
		System.out.println("Main: End of the program."+"共耗时：" + (e - s) / 1000+"."+(e - s) % 1000);
		System.out.println();
	}

	class Task extends RecursiveAction {

		private static final long serialVersionUID = 1L;
		private List<Product> products;
		private int first;
		private int last;
		private double increasement;

		public Task(List<Product> products, int first, int last, double increasement) {
			this.products = products;
			this.first = first;
			this.last = last;
			this.increasement = increasement;
		}

		@Override
		protected void compute() {
			if (last - first < threadhold) {
				updatePrices();
			} else {
				int middle = (last + first) / 2;
				System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
				Task t1 = new Task(products, first, middle + 1, increasement);
				Task t2 = new Task(products, middle + 1, last, increasement);
				invokeAll(t1, t2);
			}

		}

		private void updatePrices() {
			for (int i = first; i < last; i++) {
				Product product = products.get(i);
				product.setPrice(product.getPrice() * (1 + increasement));
				// service.update(product);
				//并发计算尽量不要过多的操作数据库，否则效率很低。
			}
		}
	}
}
