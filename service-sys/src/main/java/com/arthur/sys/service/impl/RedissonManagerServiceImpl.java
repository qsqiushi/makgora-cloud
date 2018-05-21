package com.arthur.sys.service.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthur.sys.impl.DistributedLocker;

@Service
public class RedissonManagerServiceImpl implements DistributedLocker {
  @Autowired
  private RedissonClient redissonClient;

  @Override
  public RLock lock(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock();
    return lock;
  }

  @Override
  public RLock lock(String lockKey, int leaseTime) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock(leaseTime, TimeUnit.SECONDS);
    return lock;
  }

  @Override
  public RLock lock(String lockKey, TimeUnit unit, int timeout) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock(timeout, unit);
    return lock;
  }

  @Override
  public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
    RLock lock = redissonClient.getLock(lockKey);
    try {
      return lock.tryLock(waitTime, leaseTime, unit);
    } catch (InterruptedException e) {
      return false;
    }
  }

  @Override
  public void unlock(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.unlock();
  }

  @Override
  public void unlock(RLock lock) {
    lock.unlock();
  }

  // Redisson的分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口，同时还支持自动过期解锁。
  public void testReentrantLock(RedissonClient redisson) {
    RLock lock = redisson.getLock("anyLock");
    try {
      // 1. 最常见的使用方法
      // lock.lock();
      // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
      // lock.lock(10, TimeUnit.SECONDS);
      // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
      boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
      if (res) { // 成功
        // do your business
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  // Redisson同时还为分布式锁提供了异步执行的相关方法：
  public void testAsyncReentrantLock(RedissonClient redisson) {
    RLock lock = redisson.getLock("anyLock");
    try {
      lock.lockAsync();
      lock.lockAsync(10, TimeUnit.SECONDS);
      Future<Boolean> res = lock.tryLockAsync(3, 10, TimeUnit.SECONDS);
      if (res.get()) {
        // do your business
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  // 公平锁（Fair Lock）
  // Redisson分布式可重入公平锁也是实现了java.util.concurrent.locks.Lock接口的一种RLock对象。在提供了自动过期解锁功能的同时，保证了当多个Redisson客户端线程同时请求加锁时，优先分配给先发出请求的线程。
  // [java] view plain copy
  public void testFairLock(RedissonClient redisson) {
    RLock fairLock = redisson.getFairLock("anyLock");
    try {
      // 最常见的使用方法
      fairLock.lock();
      // 支持过期解锁功能, 10秒钟以后自动解锁,无需调用unlock方法手动解锁
      fairLock.lock(10, TimeUnit.SECONDS);
      // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
      boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      fairLock.unlock();
    }
  }

  // 公平锁
  public void testAsynFairLock(RedissonClient redisson) {
    RLock fairLock = redisson.getFairLock("anyLock");
    try {
      fairLock.lockAsync();
      fairLock.lockAsync(10, TimeUnit.SECONDS);
      Future<Boolean> res = fairLock.tryLockAsync(100, 10, TimeUnit.SECONDS);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      fairLock.unlock();
    }
  }
}
