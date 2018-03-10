package modes

trait DownlinkFormat

trait VerticalStatus
object VerticalStatus extends Enumeration {
  val Airborne, OnGround = Value
}
trait VerticalStatusMessage {
  def verticalStatus: VerticalStatus
}

trait CrossLinkCapability
object CrossLinkCapability extends Enumeration {
  val NoAbility, Ability = Value
}
trait CrossLinkCapabilityMessage {
  def crossLinkCapability: CrossLinkCapability
}

trait AirAir extends VerticalStatusMessage with CrossLinkCapabilityMessage

case class FlightStatus(flightStatus: Byte)
trait FlightStatusReply {
  def flightStatus: FlightStatus
}

case class DownlinkRequest(downlinkRequest: Byte)
trait DownlinkRequestReply {
  def downlinkRequest: DownlinkRequest
}

case class UtilityMessage(utilityMessage: Short)
trait UtilityMessageReply {
  def utilityMessage: UtilityMessage
}

case class RawAddressParity(addressParity: Int)
trait AddressParityReply {
  def rawAddressParity: RawAddressParity
}

trait Reply extends DownlinkFormat with FlightStatusReply with DownlinkRequestReply with UtilityMessageReply with AddressParityReply

case class RawAltitude(altitude: Int)
trait RawAltitudeReply {
  def rawAltitude: RawAltitude
}

case class RawModeA(modeA: Int)
trait RawModeAReply {
  def rawModeA: RawModeA
}

abstract class BDSData {

}
case class BDSMBData() extends BDSData
case class BDSMVData() extends BDSData
case class BDSMEData() extends BDSData

trait LongDownlink[+BDSData] extends DownlinkFormat {
  def bdsData: BDSData
}

case class DF0(verticalStatus: VerticalStatus, crossLinkCapability: CrossLinkCapability) extends DownlinkFormat with AirAir
case class DF4(flightStatus: FlightStatus, downlinkRequest: DownlinkRequest, utilityMessage: UtilityMessage, rawAltitude: RawAltitude, rawAddressParity: RawAddressParity) extends Reply with RawAltitudeReply
case class DF5(flightStatus: FlightStatus, downlinkRequest: DownlinkRequest, utilityMessage: UtilityMessage, rawModeA: RawModeA, rawAddressParity: RawAddressParity) extends Reply with RawModeAReply
case class DF11() extends DownlinkFormat
case class DF16(bdsData: BDSMVData) extends LongDownlink[BDSMVData]
case class DF17(bdsData: BDSMEData) extends LongDownlink[BDSMEData]
case class DF18(bdsData: BDSMEData) extends LongDownlink[BDSMEData]
case class DF19(bdsData: BDSMEData) extends LongDownlink[BDSMEData]
case class DF20(flightStatus: FlightStatus, downlinkRequest: DownlinkRequest, utilityMessage: UtilityMessage, rawAltitude: RawAltitude, bdsData: BDSMBData, rawAddressParity: RawAddressParity) extends Reply with RawAltitudeReply with LongDownlink[BDSMBData]
case class DF21(flightStatus: FlightStatus, downlinkRequest: DownlinkRequest, utilityMessage: UtilityMessage, rawModeA: RawModeA, bdsData: BDSMBData, rawAddressParity: RawAddressParity) extends Reply with RawModeAReply with LongDownlink[BDSMBData]




