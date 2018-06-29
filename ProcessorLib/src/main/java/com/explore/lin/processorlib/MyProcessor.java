package com.explore.lin.processorlib;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor{

    private Messager mMessager;
    private Filer mFileUtils;
    private Elements mElementUtils;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(ClassBindActivity.class.getCanonicalName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //返回实现Messager接口的对象，用于输出控制台信息
        mMessager = processingEnv.getMessager();

        //返回实现Filer接口的对象，用于创建文件、类和辅助文件。
        mFileUtils = processingEnv.getFiler();

        //用于元素处理
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //控制台打印数据
        mMessager.printMessage(Diagnostic.Kind.NOTE,"开始处理进程Processor");
        System.out.println("开始处理进程Processor");
        Set<? extends Element> elementsToBind = roundEnvironment.getElementsAnnotatedWith(ClassBindActivity.class);
        for (Element element : elementsToBind) {
            // 判断是否Class
            TypeElement typeElement = (TypeElement) element;
            List<? extends Element> members = mElementUtils.getAllMembers(typeElement);

            MethodSpec.Builder bindViewMethodSpecBuilder = MethodSpec.methodBuilder("bind")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(TypeName.VOID)
                    .addParameter(ClassName.get(typeElement.asType()), "activity");

            for (Element item : members) {
                ClassBindView bindView = item.getAnnotation(ClassBindView.class);
                if (bindView == null){
                    continue;
                }
                bindViewMethodSpecBuilder.addStatement(
                        String.format("activity.%s = (%s) activity.findViewById(%s)",
                                item.getSimpleName(),
                                ClassName.get(item.asType()).toString(),
                                bindView.value())
                );
            }

            TypeSpec typeSpec = TypeSpec.classBuilder("BindViewFor_" + element.getSimpleName())
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(bindViewMethodSpecBuilder.build())
                    .build();
            JavaFile javaFile = JavaFile.builder(getPackageName(typeElement), typeSpec).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }
}
