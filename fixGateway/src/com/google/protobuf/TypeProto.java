// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: type.proto

package com.google.protobuf;

public final class TypeProto {
  private TypeProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_Type_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_Type_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_Field_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_Field_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_Enum_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_Enum_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_EnumValue_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_EnumValue_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_Option_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_Option_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\ntype.proto\022\017google.protobuf\032\031google/pr" +
      "otobuf/any.proto\032$google/protobuf/source" +
      "_context.proto\"\327\001\n\004Type\022\014\n\004name\030\001 \001(\t\022&\n" +
      "\006fields\030\002 \003(\0132\026.google.protobuf.Field\022\016\n" +
      "\006oneofs\030\003 \003(\t\022(\n\007options\030\004 \003(\0132\027.google." +
      "protobuf.Option\0226\n\016source_context\030\005 \001(\0132" +
      "\036.google.protobuf.SourceContext\022\'\n\006synta" +
      "x\030\006 \001(\0162\027.google.protobuf.Syntax\"\325\005\n\005Fie" +
      "ld\022)\n\004kind\030\001 \001(\0162\033.google.protobuf.Field" +
      ".Kind\0227\n\013cardinality\030\002 \001(\0162\".google.prot" +
      "obuf.Field.Cardinality\022\016\n\006number\030\003 \001(\005\022\014" +
      "\n\004name\030\004 \001(\t\022\020\n\010type_url\030\006 \001(\t\022\023\n\013oneof_" +
      "index\030\007 \001(\005\022\016\n\006packed\030\010 \001(\010\022(\n\007options\030\t" +
      " \003(\0132\027.google.protobuf.Option\022\021\n\tjson_na" +
      "me\030\n \001(\t\022\025\n\rdefault_value\030\013 \001(\t\"\310\002\n\004Kind" +
      "\022\020\n\014TYPE_UNKNOWN\020\000\022\017\n\013TYPE_DOUBLE\020\001\022\016\n\nT" +
      "YPE_FLOAT\020\002\022\016\n\nTYPE_INT64\020\003\022\017\n\013TYPE_UINT" +
      "64\020\004\022\016\n\nTYPE_INT32\020\005\022\020\n\014TYPE_FIXED64\020\006\022\020" +
      "\n\014TYPE_FIXED32\020\007\022\r\n\tTYPE_BOOL\020\010\022\017\n\013TYPE_" +
      "STRING\020\t\022\016\n\nTYPE_GROUP\020\n\022\020\n\014TYPE_MESSAGE" +
      "\020\013\022\016\n\nTYPE_BYTES\020\014\022\017\n\013TYPE_UINT32\020\r\022\r\n\tT" +
      "YPE_ENUM\020\016\022\021\n\rTYPE_SFIXED32\020\017\022\021\n\rTYPE_SF" +
      "IXED64\020\020\022\017\n\013TYPE_SINT32\020\021\022\017\n\013TYPE_SINT64" +
      "\020\022\"t\n\013Cardinality\022\027\n\023CARDINALITY_UNKNOWN" +
      "\020\000\022\030\n\024CARDINALITY_OPTIONAL\020\001\022\030\n\024CARDINAL" +
      "ITY_REQUIRED\020\002\022\030\n\024CARDINALITY_REPEATED\020\003" +
      "\"\316\001\n\004Enum\022\014\n\004name\030\001 \001(\t\022-\n\tenumvalue\030\002 \003" +
      "(\0132\032.google.protobuf.EnumValue\022(\n\007option" +
      "s\030\003 \003(\0132\027.google.protobuf.Option\0226\n\016sour" +
      "ce_context\030\004 \001(\0132\036.google.protobuf.Sourc" +
      "eContext\022\'\n\006syntax\030\005 \001(\0162\027.google.protob" +
      "uf.Syntax\"S\n\tEnumValue\022\014\n\004name\030\001 \001(\t\022\016\n\006" +
      "number\030\002 \001(\005\022(\n\007options\030\003 \003(\0132\027.google.p" +
      "rotobuf.Option\";\n\006Option\022\014\n\004name\030\001 \001(\t\022#" +
      "\n\005value\030\002 \001(\0132\024.google.protobuf.Any*.\n\006S" +
      "yntax\022\021\n\rSYNTAX_PROTO2\020\000\022\021\n\rSYNTAX_PROTO" +
      "3\020\001B}\n\023com.google.protobufB\tTypeProtoP\001Z" +
      "/google.golang.org/genproto/protobuf/pty" +
      "pe;ptype\370\001\001\242\002\003GPB\252\002\036Google.Protobuf.Well" +
      "KnownTypesb\006proto3"
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
          com.google.protobuf.AnyProto.getDescriptor(),
          com.google.protobuf.SourceContextProto.getDescriptor(),
        }, assigner);
    internal_static_google_protobuf_Type_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_protobuf_Type_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_Type_descriptor,
        new java.lang.String[] { "Name", "Fields", "Oneofs", "Options", "SourceContext", "Syntax", });
    internal_static_google_protobuf_Field_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_google_protobuf_Field_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_Field_descriptor,
        new java.lang.String[] { "Kind", "Cardinality", "Number", "Name", "TypeUrl", "OneofIndex", "Packed", "Options", "JsonName", "DefaultValue", });
    internal_static_google_protobuf_Enum_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_google_protobuf_Enum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_Enum_descriptor,
        new java.lang.String[] { "Name", "Enumvalue", "Options", "SourceContext", "Syntax", });
    internal_static_google_protobuf_EnumValue_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_google_protobuf_EnumValue_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_EnumValue_descriptor,
        new java.lang.String[] { "Name", "Number", "Options", });
    internal_static_google_protobuf_Option_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_google_protobuf_Option_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_Option_descriptor,
        new java.lang.String[] { "Name", "Value", });
    com.google.protobuf.AnyProto.getDescriptor();
    com.google.protobuf.SourceContextProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
