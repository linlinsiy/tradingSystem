// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: field_mask.proto

package com.google.protobuf;

public final class FieldMaskProto {
  private FieldMaskProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_FieldMask_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_FieldMask_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020field_mask.proto\022\017google.protobuf\"\032\n\tF" +
      "ieldMask\022\r\n\005paths\030\001 \003(\tB\211\001\n\023com.google.p" +
      "rotobufB\016FieldMaskProtoP\001Z9google.golang" +
      ".org/genproto/protobuf/field_mask;field_" +
      "mask\242\002\003GPB\252\002\036Google.Protobuf.WellKnownTy" +
      "pesb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_google_protobuf_FieldMask_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_protobuf_FieldMask_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_FieldMask_descriptor,
        new java.lang.String[] { "Paths", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}