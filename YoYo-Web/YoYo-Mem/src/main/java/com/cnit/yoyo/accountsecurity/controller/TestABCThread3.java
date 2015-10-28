package com.cnit.yoyo.accountsecurity.controller;

public class TestABCThread3 {
	private Object lock = new Object();
	private int count;

	public static void main(String[] args) {
		TestABCThread3 abc = new TestABCThread3();
		new Thread(abc.new Run("AAAAAAAAAAAAAAAA", 1)).start();
		new Thread(abc.new Run("BBBBBBBBBBBBBBBBB", 2)).start();
		new Thread(abc.new Run("CCCCCCCCCCCCCCcCC", 3)).start();
	}

	class Run implements Runnable {
		private String _name = "";
		private int _threadNum;

		public Run(String name, int threadNum) {
			_name = name;
			_threadNum = threadNum;
		}
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println(_name+"tt"+i);
				synchronized (lock) {
					System.out.println(_name+"mm"+i);
					while (true) {
						if (count % 3 == _threadNum - 1) {
							System.out.println("Count:" + i + ",Thread-Name:" + _name);
							count++;
							lock.notifyAll();
							break;
						} else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
