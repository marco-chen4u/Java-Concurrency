package topics.ProducerConsumerPattern;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ProducerConsumerDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        //建立资源“栈”
        SyncStack stack = new SyncStack();

        //生产者
        Producer2 producer = new Producer2(stack);
        //消费者
        Consumer2 consumer = new Consumer2(stack);

        //拉起线程
        threadPool.submit(producer);
        threadPool.submit(consumer);

        threadPool.shutdown();
    }
}

class SyncStack
{
    private String[] products = new String[10];
    private int index;
    public synchronized void push(String product)
    {
        if(index == product.length())
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        notify();
        products[index]=product;
        index++;
    }

    public synchronized String pop()
    {
        if(index == 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        notify();
        index--;
        String product = products[index];

        return product;
    }
    public String[] getProducts()
    {
        return products;
    }
}

//生产者线程
class Producer2 implements Runnable
{
    private SyncStack stack; //资源“栈”
    public Producer2(SyncStack stack)
    {
        this.stack = stack;
    }
    public void run()
    {
        for (int i = 0; i < stack.getProducts().length; i++)
        {
            String product = "产品"+i;
            stack.push(product);
            System.out.println("生产了: "+product);

            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

//消费者线程
class Consumer2 implements Runnable
{
    private SyncStack stack; //资源“栈”
    public Consumer2(SyncStack stack)
    {
        this.stack = stack;
    }

    public void run()
    {
        for(int i=0;i <stack.getProducts().length;i++)
        {
            String product =stack.pop();
            System.out.println("消费了: "+product);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
