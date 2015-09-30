/**
 * Generated by Scrooge
 *   version: 4.1.0
 *   rev: 87b84f89477a4737c8d57580a1e8bdaeac529b19
 *   built at: 20150928-114808
 */
package com.twitter.zipkin.thriftscala

import com.twitter.scrooge.{
  LazyTProtocol,
  TFieldBlob, ThriftException, ThriftStruct, ThriftStructCodec3, ThriftStructFieldInfo,
  ThriftStructMetaData, ThriftUtil}
import org.apache.thrift.protocol._
import org.apache.thrift.transport.{TMemoryBuffer, TTransport}
import java.nio.ByteBuffer
import java.util.Arrays
import scala.collection.immutable.{Map => immutable$Map}
import scala.collection.mutable.Builder
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, Buffer => mutable$Buffer,
  HashMap => mutable$HashMap, HashSet => mutable$HashSet}
import scala.collection.{Map, Set}


object Trace extends ThriftStructCodec3[Trace] {
  private val NoPassthroughFields = immutable$Map.empty[Short, TFieldBlob]
  val Struct = new TStruct("Trace")
  val SpansField = new TField("spans", TType.LIST, 1)
  val SpansFieldManifest = implicitly[Manifest[Seq[com.twitter.zipkin.thriftscala.Span]]]

  /**
   * Field information in declaration order.
   */
  lazy val fieldInfos: scala.List[ThriftStructFieldInfo] = scala.List[ThriftStructFieldInfo](
    new ThriftStructFieldInfo(
      SpansField,
      false,
      false,
      SpansFieldManifest,
      _root_.scala.None,
      _root_.scala.Some(implicitly[Manifest[com.twitter.zipkin.thriftscala.Span]]),
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    )
  )

  lazy val structAnnotations: immutable$Map[String, String] =
    immutable$Map.empty[String, String]

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: Trace): Unit = {
  }

  def withoutPassthroughFields(original: Trace): Trace =
    new Immutable(
      spans =
        {
          val field = original.spans
          field.map { field =>
            com.twitter.zipkin.thriftscala.Span.withoutPassthroughFields(field)
          }
        }
    )

  override def encode(_item: Trace, _oproto: TProtocol): Unit = {
    _item.write(_oproto)
  }

  private[this] def lazyDecode(_iprot: LazyTProtocol): Trace = {

    var spans: Seq[com.twitter.zipkin.thriftscala.Span] = Seq[com.twitter.zipkin.thriftscala.Span]()

    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false
    val _start_offset = _iprot.offset

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.LIST =>
    
                spans = readSpansValue(_iprot)
              case _actualType =>
                val _expectedType = TType.LIST
                throw new TProtocolException(
                  "Received wrong type for field 'spans' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new LazyImmutable(
      _iprot,
      _iprot.buffer,
      _start_offset,
      _iprot.offset,
      spans,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  override def decode(_iprot: TProtocol): Trace =
    _iprot match {
      case i: LazyTProtocol => lazyDecode(i)
      case i => eagerDecode(i)
    }

  private[this] def eagerDecode(_iprot: TProtocol): Trace = {
    var spans: Seq[com.twitter.zipkin.thriftscala.Span] = Seq[com.twitter.zipkin.thriftscala.Span]()
    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.LIST =>
                spans = readSpansValue(_iprot)
              case _actualType =>
                val _expectedType = TType.LIST
                throw new TProtocolException(
                  "Received wrong type for field 'spans' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new Immutable(
      spans,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  def apply(
    spans: Seq[com.twitter.zipkin.thriftscala.Span] = Seq[com.twitter.zipkin.thriftscala.Span]()
  ): Trace =
    new Immutable(
      spans
    )

  def unapply(_item: Trace): _root_.scala.Option[Seq[com.twitter.zipkin.thriftscala.Span]] = _root_.scala.Some(_item.spans)


  @inline private def readSpansValue(_iprot: TProtocol): Seq[com.twitter.zipkin.thriftscala.Span] = {
    val _list = _iprot.readListBegin()
    if (_list.size == 0) {
      _iprot.readListEnd()
      Nil
    } else {
      val _rv = new mutable$ArrayBuffer[com.twitter.zipkin.thriftscala.Span](_list.size)
      var _i = 0
      while (_i < _list.size) {
        _rv += {
          com.twitter.zipkin.thriftscala.Span.decode(_iprot)
        }
        _i += 1
      }
      _iprot.readListEnd()
      _rv
    }
  }

  @inline private def writeSpansField(spans_item: Seq[com.twitter.zipkin.thriftscala.Span], _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(SpansField)
    writeSpansValue(spans_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeSpansValue(spans_item: Seq[com.twitter.zipkin.thriftscala.Span], _oprot: TProtocol): Unit = {
    _oprot.writeListBegin(new TList(TType.STRUCT, spans_item.size))
    spans_item match {
      case _: IndexedSeq[_] =>
        var _i = 0
        val _size = spans_item.size
        while (_i < _size) {
          val spans_item_element = spans_item(_i)
          spans_item_element.write(_oprot)
          _i += 1
        }
      case _ =>
        spans_item.foreach { spans_item_element =>
          spans_item_element.write(_oprot)
        }
    }
    _oprot.writeListEnd()
  }


  object Immutable extends ThriftStructCodec3[Trace] {
    override def encode(_item: Trace, _oproto: TProtocol): Unit = { _item.write(_oproto) }
    override def decode(_iprot: TProtocol): Trace = Trace.decode(_iprot)
    override lazy val metaData: ThriftStructMetaData[Trace] = Trace.metaData
  }

  /**
   * The default read-only implementation of Trace.  You typically should not need to
   * directly reference this class; instead, use the Trace.apply method to construct
   * new instances.
   */
  class Immutable(
      val spans: Seq[com.twitter.zipkin.thriftscala.Span],
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends Trace {
    def this(
      spans: Seq[com.twitter.zipkin.thriftscala.Span] = Seq[com.twitter.zipkin.thriftscala.Span]()
    ) = this(
      spans,
      Map.empty
    )
  }

  /**
   * This is another Immutable, this however keeps strings as lazy values that are lazily decoded from the backing
   * array byte on read.
   */
  private[this] class LazyImmutable(
      _proto: LazyTProtocol,
      _buf: Array[Byte],
      _start_offset: Int,
      _end_offset: Int,
      val spans: Seq[com.twitter.zipkin.thriftscala.Span],
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends Trace {

    override def write(_oprot: TProtocol): Unit = {
      _oprot match {
        case i: LazyTProtocol => i.writeRaw(_buf, _start_offset, _end_offset - _start_offset)
        case _ => super.write(_oprot)
      }
    }


    /**
     * Override the super hash code to make it a lazy val rather than def.
     *
     * Calculating the hash code can be expensive, caching it where possible
     * can provide signifigant performance wins. (Key in a hash map for instance)
     * Usually not safe since the normal constructor will accept a mutable map or
     * set as an arg
     * Here however we control how the class is generated from serialized data.
     * With the class private and the contract that we throw away our mutable references
     * having the hash code lazy here is safe.
     */
    override lazy val hashCode = super.hashCode
  }

  /**
   * This Proxy trait allows you to extend the Trace trait with additional state or
   * behavior and implement the read-only methods from Trace using an underlying
   * instance.
   */
  trait Proxy extends Trace {
    protected def _underlying_Trace: Trace
    override def spans: Seq[com.twitter.zipkin.thriftscala.Span] = _underlying_Trace.spans
    override def _passthroughFields = _underlying_Trace._passthroughFields
  }
}

trait Trace
  extends ThriftStruct
  with scala.Product1[Seq[com.twitter.zipkin.thriftscala.Span]]
  with java.io.Serializable
{
  import Trace._

  def spans: Seq[com.twitter.zipkin.thriftscala.Span]

  def _passthroughFields: immutable$Map[Short, TFieldBlob] = immutable$Map.empty

  def _1 = spans


  /**
   * Gets a field value encoded as a binary blob using TCompactProtocol.  If the specified field
   * is present in the passthrough map, that value is returned.  Otherwise, if the specified field
   * is known and not optional and set to None, then the field is serialized and returned.
   */
  def getFieldBlob(_fieldId: Short): _root_.scala.Option[TFieldBlob] = {
    lazy val _buff = new TMemoryBuffer(32)
    lazy val _oprot = new TCompactProtocol(_buff)
    _passthroughFields.get(_fieldId) match {
      case blob: _root_.scala.Some[TFieldBlob] => blob
      case _root_.scala.None => {
        val _fieldOpt: _root_.scala.Option[TField] =
          _fieldId match {
            case 1 =>
              if (spans ne null) {
                writeSpansValue(spans, _oprot)
                _root_.scala.Some(Trace.SpansField)
              } else {
                _root_.scala.None
              }
            case _ => _root_.scala.None
          }
        _fieldOpt match {
          case _root_.scala.Some(_field) =>
            val _data = Arrays.copyOfRange(_buff.getArray, 0, _buff.length)
            _root_.scala.Some(TFieldBlob(_field, _data))
          case _root_.scala.None =>
            _root_.scala.None
        }
      }
    }
  }

  /**
   * Collects TCompactProtocol-encoded field values according to `getFieldBlob` into a map.
   */
  def getFieldBlobs(ids: TraversableOnce[Short]): immutable$Map[Short, TFieldBlob] =
    (ids flatMap { id => getFieldBlob(id) map { id -> _ } }).toMap

  /**
   * Sets a field using a TCompactProtocol-encoded binary blob.  If the field is a known
   * field, the blob is decoded and the field is set to the decoded value.  If the field
   * is unknown and passthrough fields are enabled, then the blob will be stored in
   * _passthroughFields.
   */
  def setField(_blob: TFieldBlob): Trace = {
    var spans: Seq[com.twitter.zipkin.thriftscala.Span] = this.spans
    var _passthroughFields = this._passthroughFields
    _blob.id match {
      case 1 =>
        spans = readSpansValue(_blob.read)
      case _ => _passthroughFields += (_blob.id -> _blob)
    }
    new Immutable(
      spans,
      _passthroughFields
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetField(_fieldId: Short): Trace = {
    var spans: Seq[com.twitter.zipkin.thriftscala.Span] = this.spans

    _fieldId match {
      case 1 =>
        spans = Seq[com.twitter.zipkin.thriftscala.Span]()
      case _ =>
    }
    new Immutable(
      spans,
      _passthroughFields - _fieldId
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetSpans: Trace = unsetField(1)


  override def write(_oprot: TProtocol): Unit = {
    Trace.validate(this)
    _oprot.writeStructBegin(Struct)
    if (spans ne null) writeSpansField(spans, _oprot)
    if (_passthroughFields.nonEmpty) {
      _passthroughFields.values.foreach { _.write(_oprot) }
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    spans: Seq[com.twitter.zipkin.thriftscala.Span] = this.spans,
    _passthroughFields: immutable$Map[Short, TFieldBlob] = this._passthroughFields
  ): Trace =
    new Immutable(
      spans,
      _passthroughFields
    )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Trace]

  override def equals(other: Any): Boolean =
    canEqual(other) &&
      _root_.scala.runtime.ScalaRunTime._equals(this, other) &&
      _passthroughFields == other.asInstanceOf[Trace]._passthroughFields

  override def hashCode: Int = _root_.scala.runtime.ScalaRunTime._hashCode(this)

  override def toString: String = _root_.scala.runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 1

  override def productElement(n: Int): Any = n match {
    case 0 => this.spans
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "Trace"
}