package zio.dynamodb

import zio.dynamodb.KeyConditionExpr.PartitionKeyEquals
import zio.dynamodb.proofs.RefersTo

// belongs to the package top level
private[dynamodb] final case class PartitionKey2[-From, +To](keyName: String) { self =>
  def ===[To1 >: To, To2: ToAttributeValue, IsPrimaryKey](
    value: To2
  )(implicit ev: RefersTo[To1, To2]): PartitionKeyEquals[From, To] = {
    val _ = ev
    PartitionKeyEquals(self, implicitly[ToAttributeValue[To2]].toAttributeValue(value))
  }
}
