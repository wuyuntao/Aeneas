package com.wuyuntao.aeneas.journal

import scala.collection.immutable.Seq
import scala.concurrent.Future
import scala.util.Try

import akka.actor.ActorLogging
import akka.persistence.AtomicWrite
import akka.persistence.PersistentRepr
import akka.persistence.journal.AsyncWriteJournal

class AeneasJournal extends AsyncWriteJournal with ActorLogging {

  def asyncWriteMessages(messages: Seq[AtomicWrite]): Future[Seq[Try[Unit]]] = {
    throw new NotImplementedError()
  }

  def asyncDeleteMessagesTo(persistenceId: String, toSequenceNr: Long): Future[Unit] = {
    throw new NotImplementedError()
  }

  def asyncReplayMessages(persistenceId: String, fromSequenceNr: Long, toSequenceNr: Long, max: Long)(recoveryCallback: PersistentRepr => Unit): Future[Unit] = {
    throw new NotImplementedError()
  }

  def asyncReadHighestSequenceNr(persistenceId: String, fromSequenceNr: Long): Future[Long] = {
    throw new NotImplementedError()
  }
}