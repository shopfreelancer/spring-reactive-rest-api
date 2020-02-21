package com.sf.restapi.client.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultTest {
    private String SEARCH_RESULT_JSON = "{\"id\":\"BjHE2IjfaJWA_w9rSQu3l6\",\"thumbnailUrl\":\"https://dam.sharepa.com:8443/thumbnail/BjHE2IjfaJWA_w9rSQu3l6/*/2002-08_04_V01_C03_2000x2000_J_15013_Szene_1_thumb.jpg?_=2\",\"previewUrl\":\"https://dam.sharepa.com:8443/preview/BjHE2IjfaJWA_w9rSQu3l6/previews/maxWidth_1600_maxHeight_1600.jpg/*/2002-08_04_V01_C03_2000x2000_J_15013_Szene_1_preview.jpg?_=2\",\"originalUrl\":\"https://dam.sharepa.com:8443/file/BjHE2IjfaJWA_w9rSQu3l6/*/2002-08_04_V01_C03_2000x2000_J_15013_Szene_1.png?_=3\",\"metadata\":{\"resolution\":\"72 px/inch\",\"originalDocumentId\":\"xmp.did:63586021-ff11-144c-ab25-a70c89b8f621\",\"height\":2000,\"assetType\":\"png\",\"dimensionMm\":\"706 x 706 mm\",\"filename\":\"2002-08_04_V01_C03_2000x2000_J_15013_Szene_1.png\",\"assetCreated\":{\"value\":1582296302361,\"formatted\":\"2020-02-21 15:45:02 +0100\"},\"basicDataETag\":2,\"assetFileModifier\":\"SUP-SzTw-02019\",\"widthIn\":27.77777777777778,\"heightMm\":705.5555555555555,\"assetCreator\":\"SUP-SzTw-02019\",\"mimeType\":\"image/png\",\"resolutionUnit\":2,\"resolutionY\":72.0,\"resolutionX\":72.0,\"imageAspectRatio\":{\"value\":1.0,\"formatted\":\"1.0\"},\"assetFileModified\":{\"value\":1582296305288,\"formatted\":\"2020-02-21 15:45:05 +0100\"},\"name\":\"2002-08_04_V01_C03_2000x2000_J_15013_Szene_1.png\",\"dimensionIn\":\"27.78 x 27.78 inch\",\"imageOrientation\":\"Square\",\"heightPt\":2000.0,\"xmpCreated\":{\"value\":1582296091000,\"formatted\":\"2020-02-21 15:41:31 +0100\"},\"placeholder\":\"false\",\"versionETag\":1,\"documentId\":\"adobe:docid:photoshop:d4e28fa3-a65c-8147-be6c-4fb106d1b71e\",\"fileType\":\"PNG\",\"software\":\"Adobe Photoshop 21.0 (Windows)\",\"folderPath\":\"/Client/OBI Group Holding SE & Co. KGaA/Joyful Content Images/2002-08_Balkon_KW_11\",\"xmpModifyDate\":{\"value\":1582296293000,\"formatted\":\"2020-02-21 15:44:53 +0100\"},\"xmpMetadataDate\":{\"value\":1582296293000,\"formatted\":\"2020-02-21 15:44:53 +0100\"},\"assetDomain\":\"image\",\"bitDepth\":16,\"downloadCount\":0,\"extension\":\"png\",\"instanceId\":\"xmp.iid:4ce49f0b-1372-c842-a0a1-2d654c286aa8\",\"assetPath\":\"/Client/OBI Group Holding SE & Co. KGaA/Joyful Content Images/2002-08_Balkon_KW_11/2002-08_04_V01_C03_2000x2000_J_15013_Szene_1.png\",\"partitionId\":189,\"width\":2000,\"widthPt\":2000.0,\"assetModified\":{\"value\":1582296306934,\"formatted\":\"2020-02-21 15:45:06 +0100\"},\"autoRenameNumber\":0,\"baseName\":\"2002-08_04_V01_C03_2000x2000_J_15013_Szene_1\",\"dimensionPt\":\"2000 x 2000 pt\",\"thumbnailState\":\"yes\",\"colorMode\":\"3\",\"assetModifier\":\"SUP-SzTw-02019\",\"fileSize\":{\"value\":19145784,\"formatted\":\"18.3 MB\"},\"fileCreated\":{\"value\":1582296305131,\"formatted\":\"2020-02-21 15:45:05 +0100\"},\"previewState\":\"yes\",\"metadataComplete\":\"true\",\"contentETag\":3,\"viewCount\":0,\"dimension\":\"2000 x 2000 px\",\"fileModified\":{\"value\":1582296305000,\"formatted\":\"2020-02-21 15:45:05 +0100\"},\"previewETag\":2,\"assetPropertyETag\":1,\"versionNumber\":1,\"widthMm\":705.5555555555555,\"heightIn\":27.77777777777778},\"permissions\":\"VPWUMERXC-\"}";

    @Test
    public void mapNestedAttributesSuccess() throws IOException {
        SearchResult searchResult = new ObjectMapper().readerFor(SearchResult.class)
                .readValue(SEARCH_RESULT_JSON);

        assertEquals(searchResult.getFilename(), "2002-08_04_V01_C03_2000x2000_J_15013_Szene_1.png");
    }
}