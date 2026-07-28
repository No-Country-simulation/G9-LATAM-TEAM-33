import io
import json
import logging
import oci

from fdk import response

def handler(ctx, data: io.BytesIO=None):
    try:
        signer = oci.auth.signers.get_resource_principals_signer()
        object_storage = oci.object_storage.ObjectStorageClient(config={}, signer=signer)
        namespace = object_storage.get_namespace().data
    except Exception as ex:
        logging.getLogger().exception('failed to access Object Storage, ' + str(ex))
        raise

    firstNumber = 0
    secondNumber = 0
    constant = 1

    try:
        # get object from bucket
        bucket_response = object_storage.get_object(namespace_name=namespace, bucket_name="hackathon-model-bucket", object_name="demo/test_object.json")
        content = bucket_response.data.content
        jsonContent = json.loads(content)
    
        body = json.loads(data.getvalue())
        firstNumber = body.get("firstNumber")
        secondNumber = body.get("secondNumber")
        constant = jsonContent.get('constant')
    except (Exception, ValueError) as ex:
        logging.getLogger().info('error parsing json payload: ' + str(ex))

    result = (firstNumber + secondNumber) * constant
    message = f"{firstNumber} plus {secondNumber} multiplied by the constant: {constant} is equal to = {(firstNumber + secondNumber) * constant}"

    return response.Response(
        ctx, response_data=json.dumps(
            {"firstNumber": firstNumber, "secondNumber": secondNumber, "result": result, "message": message}),
        headers={"Content-Type": "application/json"}
    )
